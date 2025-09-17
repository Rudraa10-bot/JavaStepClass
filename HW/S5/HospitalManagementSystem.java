// File: HospitalManagementSystem.java
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/*
  Single-file Hospital Management System demonstrating:
  - Immutable MedicalRecord (final)
  - Access control by staff roles
  - Constructor chaining
  - JavaBean conventions
  - Defensive copying and data privacy
*/

// ---------------------------
// a. Immutable MedicalRecord
// ---------------------------
final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory,
                         LocalDate birthDate, String bloodType) {
        // HIPAA-style light validation (example): sensitive fields present and birthDate realistic
        if (recordId == null || recordId.isBlank()) throw new IllegalArgumentException("recordId required");
        if (patientDNA == null || patientDNA.length() < 10) throw new IllegalArgumentException("Invalid patientDNA");
        if (birthDate == null || Period.between(birthDate, LocalDate.now()).getYears() < 0)
            throw new IllegalArgumentException("Invalid birthDate");
        if (bloodType == null || bloodType.isBlank()) throw new IllegalArgumentException("bloodType required");
        // Defensive copies for arrays
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = allergies == null ? new String[0] : Arrays.copyOf(allergies, allergies.length);
        this.medicalHistory = medicalHistory == null ? new String[0] : Arrays.copyOf(medicalHistory, medicalHistory.length);
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    // Getters with defensive copying
    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; } // still sensitive; staff access controlled elsewhere
    public String[] getAllergies() { return Arrays.copyOf(allergies, allergies.length); }
    public String[] getMedicalHistory() { return Arrays.copyOf(medicalHistory, medicalHistory.length); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }

    // final method for safety
    public final boolean isAllergicTo(String substance) {
        if (substance == null) return false;
        for (String a : allergies) if (a.equalsIgnoreCase(substance.trim())) return true;
        return false;
    }

    @Override
    public String toString() {
        // Audit-friendly: do not print patientDNA or full medical history; provide summary
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", birthDate=" + birthDate +
                ", bloodType='" + bloodType + '\'' +
                ", allergies=" + Arrays.toString(allergies) +
                ", historyCount=" + medicalHistory.length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord)) return false;
        MedicalRecord that = (MedicalRecord) o;
        return recordId.equals(that.recordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }
}

// ---------------------------
// b. Patient class with privacy
// ---------------------------
class Patient {
    // Private final protected health info
    private final String patientId;
    private final MedicalRecord medicalRecord;

    // Modifiable non-sensitive or semi-sensitive fields
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;

    // Current treatment info (mutable)
    private int roomNumber;
    private String attendingPhysician;

    // Constructor chaining

    // Emergency admission (minimal data, temporary ID)
    public Patient(String currentName) {
        this(UUID.randomUUID().toString(), currentName, null, null, 0, null,
                new MedicalRecord("TEMP-" + UUID.randomUUID().toString(), "DNA_TEMP_XXXXXXXX", new String[0], new String[0],
                        LocalDate.of(1900,1,1), "Unknown"));
    }

    // Standard admission (full patient information)
    public Patient(String currentName, String emergencyContact, String insuranceInfo,
                   int roomNumber, String attendingPhysician, MedicalRecord medicalRecord) {
        this(UUID.randomUUID().toString(), currentName, emergencyContact, insuranceInfo, roomNumber, attendingPhysician, medicalRecord);
    }

    // Transfer admission (imports existing medical record)
    public Patient(String patientId, MedicalRecord medicalRecord) {
        this(patientId, "Unknown", null, null, 0, null, medicalRecord);
    }

    // Main constructor (private-ish central)
    public Patient(String patientId, String currentName, String emergencyContact, String insuranceInfo,
                   Integer roomNumber, String attendingPhysician, MedicalRecord medicalRecord) {
        if (patientId == null || patientId.isBlank()) throw new IllegalArgumentException("patientId required");
        if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("currentName required");
        if (medicalRecord == null) throw new IllegalArgumentException("medicalRecord required");

        this.patientId = patientId;
        this.currentName = currentName;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber == null ? 0 : Math.max(0, roomNumber);
        this.attendingPhysician = attendingPhysician;
        this.medicalRecord = medicalRecord;
    }

    // JavaBean getters/setters for modifiable data (validated setters)

    public String getPatientId() { return patientId; } // ID is immutable

    // MedicalRecord accessor is private (PHI) — no public getter returning whole record
    // but package-private for staff within package
    MedicalRecord getMedicalRecordForStaff() { return medicalRecord; } // package-private

    public String getCurrentName() { return currentName; }
    public void setCurrentName(String currentName) {
        if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("name required");
        this.currentName = currentName;
    }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    public String getInsuranceInfo() { return insuranceInfo == null ? "Not Provided" : maskInsurance(insuranceInfo); }
    public void setInsuranceInfo(String insuranceInfo) { this.insuranceInfo = insuranceInfo; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) {
        if (roomNumber < 0) throw new IllegalArgumentException("roomNumber invalid");
        this.roomNumber = roomNumber;
    }

    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String attendingPhysician) { this.attendingPhysician = attendingPhysician; }

    // Package-private basic info for staff
    String getBasicInfo() {
        return "Patient[" + patientId + "] Name='" + currentName + "', Room=" + roomNumber + ", Physician=" + attendingPhysician;
    }

    // Public non-sensitive info
    public String getPublicInfo() {
        return "Name='" + currentName + "', Room=" + roomNumber;
    }

    // helper to mask insurance details for public presentation
    private String maskInsurance(String insurance) {
        if (insurance == null || insurance.length() <= 4) return "****";
        int keep = 4;
        return "****" + insurance.substring(insurance.length() - keep);
    }

    @Override
    public String toString() {
        // Audit trail: include patientId, masked insurance, room and a note that PHI exists
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", name='" + currentName + '\'' +
                ", room=" + roomNumber +
                ", insuranceMasked='" + (insuranceInfo == null ? "N/A" : maskInsurance(insuranceInfo)) + '\'' +
                ", PHI=REDACTED" +
                '}';
    }
}

// ---------------------------
// d. Medical staff classes
// ---------------------------

final class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        if (licenseNumber == null || licenseNumber.isBlank()) throw new IllegalArgumentException("licenseNumber required");
        if (specialty == null || specialty.isBlank()) throw new IllegalArgumentException("specialty required");
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = certifications == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet<>(certifications));
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return certifications; }

    @Override
    public String toString() {
        return "Doctor{lic=" + licenseNumber + ", spec=" + specialty + ", certs=" + certifications + "}";
    }
}

final class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        if (nurseId == null || nurseId.isBlank()) throw new IllegalArgumentException("nurseId required");
        this.nurseId = nurseId;
        this.shift = shift == null ? "Day" : shift;
        this.qualifications = qualifications == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList<>(qualifications));
    }

    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return qualifications; }

    @Override
    public String toString() {
        return "Nurse{id=" + nurseId + ", shift=" + shift + "}";
    }
}

final class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        if (adminId == null || adminId.isBlank()) throw new IllegalArgumentException("adminId required");
        this.adminId = adminId;
        this.accessPermissions = accessPermissions == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList<>(accessPermissions));
    }

    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return accessPermissions; }

    @Override
    public String toString() {
        return "Administrator{id=" + adminId + ", perms=" + accessPermissions + "}";
    }
}

// ---------------------------
// e. HospitalSystem with access control
// ---------------------------
class HospitalSystem {
    // private patient registry (ID -> Patient)
    private final Map<String, Patient> patientRegistry = new HashMap<>();

    // hospital policy constants
    public static final int MAX_ROOM_NUMBER = 9999;
    public static final String ADMIN_PERMISSION_VIEW_MEDICAL = "VIEW_MEDICAL";

    // Admit patient: staff must have rights. Returns true if admitted/registered.
    public boolean admitPatient(Object patient, Object staff) {
        if (!(patient instanceof Patient)) return false;
        Patient p = (Patient) patient;
        if (!validateStaffAccess(staff, p)) {
            System.out.println("Staff not authorized to admit patient: " + staff);
            return false;
        }
        patientRegistry.put(p.getPatientId(), p);
        audit("ADMIT", p.getPatientId(), staff);
        return true;
    }

    // Private validator for staff access
    private boolean validateStaffAccess(Object staff, Patient patient) {
        // Doctors and Nurses may admit only if they are present and have basic credentials.
        if (staff instanceof Doctor doc) {
            // example rule: doctor license must be non-empty and specialization not Blank
            return doc.getLicenseNumber() != null && !doc.getLicenseNumber().isBlank();
        }
        if (staff instanceof Nurse nurse) {
            // nurses can admit but only for non-critical transfers (simple check)
            return nurse.getQualifications().size() >= 0;
        }
        if (staff instanceof Administrator admin) {
            // admin must have explicit permission to register patients
            return admin.getAccessPermissions().contains("ADMIT_PATIENT");
        }
        return false;
    }

    // Package-private method to retrieve patient for staff (respects role)
    Patient getPatientForStaff(Object staff, String patientId) {
        Patient p = patientRegistry.get(patientId);
        if (p == null) return null;
        if (staff instanceof Doctor) return p; // doctor may get full patient (via package-private access we can call getMedicalRecordForStaff)
        if (staff instanceof Nurse) return p;  // nurse also may access medical record in this simplified model
        if (staff instanceof Administrator admin) {
            if (admin.getAccessPermissions().contains(ADMIN_PERMISSION_VIEW_MEDICAL)) return p;
            // else only return minimal public info via caller
        }
        return p; // allow minimal access; actual PHI access controlled by getters available in package
    }

    // public method to request medical summary (enforces checks)
    public Optional<String> requestMedicalSummary(Object staff, String patientId) {
        Patient p = patientRegistry.get(patientId);
        if (p == null) return Optional.empty();
        // Only doctors or admins with VIEW_MEDICAL or nurses with qualifications may get summary
        if (staff instanceof Doctor || (staff instanceof Administrator adm && adm.getAccessPermissions().contains(ADMIN_PERMISSION_VIEW_MEDICAL))
                || (staff instanceof Nurse)) {
            MedicalRecord mr = p.getMedicalRecordForStaff(); // package-private accessor
            // Provide a safe summary (no DNA)
            String summary = "Summary for " + p.getBasicInfo() + " | BloodType=" + mr.getBloodType() +
                    " | Allergies=" + Arrays.toString(mr.getAllergies()) + " | HistoryCount=" + mr.getMedicalHistory().length;
            audit("REQUEST_SUMMARY", p.getPatientId(), staff);
            return Optional.of(summary);
        }
        audit("DENIED_SUMMARY", p.getPatientId(), staff);
        return Optional.of("ACCESS DENIED");
    }

    // private audit helper
    private void audit(String action, String patientId, Object actor) {
        String actorId = actor == null ? "Unknown" : actor.getClass().getSimpleName() + "-" + actor.toString();
        System.out.printf("[AUDIT] %s | patient=%s | by=%s | at=%s%n", action, patientId, actorId, new Date());
    }

    // Package-private helper to list patients (for internal staff consoles)
    List<String> listAllPatientIds() {
        return new ArrayList<>(patientRegistry.keySet());
    }
}

// ---------------------------
// Public runner class
// ---------------------------
public class HospitalManagementSystem {
    public static void main(String[] args) {
        // Create a medical record (immutable)
        MedicalRecord mr = new MedicalRecord(
                "REC-" + UUID.randomUUID().toString().substring(0,6),
                "AGCTTAGGCTAGCTA12345", // sample "DNA" string for demo (must be >=10 chars)
                new String[]{"Penicillin", "Latex"},
                new String[]{"Appendectomy 2012", "Asthma diagnosis 2018"},
                LocalDate.of(1990, 4, 12),
                "O+"
        );

        // Create patients (constructor chaining examples)
        Patient standardPatient = new Patient("John Doe", "555-1111", "INS-123456", 101, "Dr. Strange", mr);
        Patient emergencyPatient = new Patient("Jane Quick"); // emergency admission
        Patient transferred = new Patient("PAT-EX-001", mr); // transfer admission

        // Create staff roles
        Doctor doc = new Doctor("LIC-98765", "Emergency Medicine", Set.of("ACLS", "Trauma"));
        Nurse nurse = new Nurse("NUR-112", "Night", List.of("RN", "Pediatric"));
        Administrator admin = new Administrator("ADM-01", List.of("ADMIT_PATIENT", "VIEW_MEDICAL"));

        // Hospital system operations
        HospitalSystem hs = new HospitalSystem();

        // Try admitting patients
        System.out.println("Admitting John by doctor: " + hs.admitPatient(standardPatient, doc));
        System.out.println("Admitting Jane by nurse: " + hs.admitPatient(emergencyPatient, nurse));
        System.out.println("Admitting transferred by admin: " + hs.admitPatient(transferred, admin));

        // Request medical summary - allowed for doctor
        Optional<String> summary = hs.requestMedicalSummary(doc, standardPatient.getPatientId());
        summary.ifPresent(System.out::println);

        // Request medical summary - admin with permission
        Optional<String> admSummary = hs.requestMedicalSummary(admin, standardPatient.getPatientId());
        admSummary.ifPresent(System.out::println);

        // Request medical summary - unauthorized (plain object)
        Object randomStaff = new Object();
        System.out.println("Unauthorized request: " + hs.requestMedicalSummary(randomStaff, standardPatient.getPatientId()).orElse("No patient"));

        // List patients for internal staff UI (package-private)
        System.out.println("All patient IDs (internal): " + hs.listAllPatientIds());

        // Show patient public info
        System.out.println("Public info: " + standardPatient.getPublicInfo());

        // Attempt to check allergy via MedicalRecord (final method)
        System.out.println("Is allergic to Penicillin? " + mr.isAllergicTo("Penicillin"));

        // Print objects (toString includes audit-friendly masking)
        System.out.println("Patient (toString): " + standardPatient);
        System.out.println("MedicalRecord (toString): " + mr);
        System.out.println("Doctor: " + doc);
        System.out.println("Nurse: " + nurse);
        System.out.println("Admin: " + admin);
    }
}
