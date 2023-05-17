package CleanNow01.CleanNow.Models;

public class Limpiador {
        private int dni;
        private String name;
        private String lastName;
        private String email;
        private int phone;
        private boolean checked_records;
        private boolean available;
        
        public Limpiador(int dni, String name, String lastName, String email, int phone, boolean checked_records, boolean available) {
            this.dni = dni;
            this.name = name;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.checked_records = checked_records;
            this.available = available;
        }
        
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }
    
        public String getLastName() {
            return lastName;
        }
    
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        
        public int getDni() {
            return dni;
        }

        public void setDni(int dni) {
            this.dni = dni;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isChecked_records() {
            return checked_records;
        }

        public void setChecked_records(boolean checked_records) {
            this.checked_records = checked_records;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
       
    }
    
