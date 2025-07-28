INSERT INTO patient (id, name, email, gender, blood_group) VALUES
(1, 'Aarav Sharma', 'aarav.sharma@example.com', 'Male', 'O_POSITIVE'),
(2, 'Saanvi Patel', 'saanvi.patel@example.com', 'Female', 'A_NEGATIVE'),
(3, 'Vivaan Singh', 'vivaan.singh@example.com', 'Male', 'B_POSITIVE'),
(4, 'Myra Gupta', 'myra.gupta@example.com', 'Female', 'AB_POSITIVE'),
(5, 'Reyansh Kumar', 'reyansh.kumar@example.com', 'Male', 'O_NEGATIVE');


INSERT INTO  doctor (name,specialization,email)
values
('Dr. Rakesh Mehta','Cardiology','rakesh.mehta@example.com'),
('Dr. Sneha Kapoor','Dermatalogy','sneha.kapoor@example.com'),
('Dr. Arjun Nair','Orthopaedics','arjun.nair@example.com');


INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
  ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
  ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
  ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
  ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
  ('2025-07-05 16:15:00', 'Consultation', 1, 4),
  ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);