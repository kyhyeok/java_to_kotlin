package nesting;

public class JavaHouse {

    private String address;

    private LivingRoom livingRoom;

    public JavaHouse(String address) {
        this.address = address;
        this.livingRoom = new LivingRoom(10);
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }
    // 권장되지 않는 내부 클래스
//    public class LivingRoom {
//        private double area;
//
//        public LivingRoom(double area) {
//            this.area = area;
//        }
//
//        public String getAddress() {
//            return JavaHouse.this.address; // 바깥 클래스와 연결되어 있다
//        }
//    }


    // 권장되는 static 중첩 클래스
    public static class LivingRoom { // static이 붙을 경우애는
        private double area;

        public LivingRoom(double area) {
            this.area = area;
        }

        // static이 붙을 경우애는 바깥 클래스를 바로 불러올 수 없다
        // public String getAddress() {
            // return JavaHouse.this.address;
        // }
    }
}
