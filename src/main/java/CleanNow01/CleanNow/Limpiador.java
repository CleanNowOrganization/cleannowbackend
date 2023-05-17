package CleanNow01.CleanNow;

public class Limpiador {
        private String name;
        private String lastName;
        private int quality;
        
        
        public Limpiador(String name, String lastName, int quality, int speed, int reliability) {
            this.name = name;
            this.lastName = lastName;
            this.quality = quality;

        }
        
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getLastName() {
            return lastName;
        }
    
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    
        public int getQuality() {
            return quality;
        }
        
        public void setQuality(int quality) {
            this.quality = quality;
        }
    
       
    }
    
