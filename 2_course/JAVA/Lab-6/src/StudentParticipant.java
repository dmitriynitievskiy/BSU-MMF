public class StudentParticipant extends Student {

    public StudentParticipant(String name, String id) {
        super(name, id);
    }

    @Override
    public void Study() {
        System.out.println("Студент учится!");
    }
}
