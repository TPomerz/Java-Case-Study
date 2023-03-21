export class User {
    id?: number | null;
    email?: string;
    password?: string | null;
    firstName?: string;
    lastName?: string;
    country?: string;

    constructor (id?: number | null, email?: string, password?: string | null, firstName?: string, lastName?: string, country?: string) {
        this.id = id;
        this.email = email; 
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}