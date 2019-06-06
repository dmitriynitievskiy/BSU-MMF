public class Runner {
    public static void main(String[ ] args) {
        function();
    }

    public static void function(){
        Entrant student = new StudentParticipant
                ("Nitievsky Dmitry", "123456789");
        Entrant student2 = new StudentParticipant
                ("Egorov Aleksey","12221");

        System.out.println(student.getName());
        System.out.println(student.getId());
        student.Study();

        System.out.println(student2.getName());
        System.out.println(student2.getId());
        student2.Study();
    }
}
