package AnonymousClassChallange;

import java.time.LocalDate;
import java.util.*;
record Employee(String firstName, String lastName, int hireDate) {
}

public class Run {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Stefan", "Fotev",2015),
                new Employee("Ivan","Manolov",2014),
                new Employee("Simeon","Petrov",2020),
                new Employee("Alex","Svetoslavov",2004)
        ));

        workWithList(employees);
    }

    static void workWithList(List<Employee> employees){

        class LocalClass{
            private Employee employee;
            private String fullName;
            private int yearsWorked;

            public LocalClass(Employee employee) {
                yearsWorked = LocalDate.now().getYear() - employee.hireDate();
                fullName = employee.firstName() + " " + employee.lastName();
            }
            @Override
            public String toString() {
                return "%-30s %-15s %d".formatted(fullName,"worked years",yearsWorked);
            }
        }
        List<LocalClass> localEmployees = new ArrayList<>(employees.size());
        for(var employee : employees){
            localEmployees.add(new LocalClass(employee));
        }

        localEmployees.forEach((l) -> {
            System.out.println(l);
        });
        System.out.println();


        var comparator = new Comparator<LocalClass>() {
            @Override
            public int compare(LocalClass o1, LocalClass o2) {
                return o1.fullName.compareTo(o2.fullName);
            }
        };

        System.out.println("-".repeat(10) + "Sorted by full name");
        localEmployees.sort(comparator);
        localEmployees.forEach((l) -> {
            System.out.println(l);
        });
    }
}
