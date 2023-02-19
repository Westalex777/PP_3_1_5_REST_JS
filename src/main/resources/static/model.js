export class User {
    constructor(id, firstname, lastname, age, email, roles, password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age
        this.email = email;
        this.roles = roles;
        this.password = password;
    }
}

export class Role {
    constructor(id, nameNotPrefix, name) {
        this.id = id;
        this.nameNotPrefix = nameNotPrefix;
        this.name = name;
    }
}