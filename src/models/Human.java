package models;

/**
 * User: k33g_org
 * Date: 12/23/12
 * Time: 3:54 PM
 */
public class Human {
    public String firstName;
    public String lastName;
    public String id;

    public Human() {
    }

    public Human(String firstName, String lastName) {
        this.id=null;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Human{" + "firstName=" + firstName +
                ", lastName=" + lastName + ", id=" + id + '}';
    }
}
