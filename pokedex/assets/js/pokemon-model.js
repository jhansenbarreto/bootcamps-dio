
class Pokemon {
    number;
    name;
    type;
    types = [];
    photo;
    stats = [];
    weight;
    height;
}

class Stat {
    constructor(name, value) {
        this.name = name;
        this.value = value;
    }
}