
class Coin{

    constructor(pos,color,r) {
        this.pos = pos
        this.r = r
        this.color = color
    }

    draw(){

        fill(this.color);
        stroke(0);
        circle(this.pos.x,this.pos.y,this.r)

    }

}