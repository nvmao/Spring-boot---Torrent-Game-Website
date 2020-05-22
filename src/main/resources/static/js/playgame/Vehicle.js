
class Vehicle{

    constructor(pos) {
        this.pos = pos
        this.vel = createVector(0,0)
        this.acc = createVector(0,0)
        this.maxSpeed = 5.0
        this.maxForce = 0.2

        this.color = color(175,175,12)



        this.target = createVector(0,0)
    }

    update(){
        this.seek()

        this.vel.add(this.acc)
        this.vel.limit(this.maxSpeed)
        this.pos.add(this.vel)
        this.acc.mult(0)


        this.draw()
        this.sendToServer()
    }


    applyForce(force){
        this.acc.add(force)
    }

    seekTo(target){
        this.target = target
    }

    seek(){

        let desired = createVector(this.target.x - this.pos.x,this.target.y-this.pos.y)
        desired.normalize()
        desired.mult(this.maxSpeed)

        let  steer = desired.sub(this.vel)
        steer.limit(this.maxForce)

        this.applyForce(steer)

    }

    draw(){
        let r =7.0
        let theta = this.vel.heading() + PI/2;
        fill(this.color);
        stroke(0);
        push();
        translate(this.pos.x,this.pos.y);
        rotate(theta);
        beginShape();
        vertex(0, -r*2);
        vertex(-r,r*2);
        vertex(r, r*2);
        endShape(CLOSE);
        pop();
    }

    getPosFromServer(pos,vel,target){
        this.pos =  pos
        this.vel = vel
        this.target = target
    }

    sendToServer(){
        stompClient.send('/app/playgame2/position/'+enemy.userName,{},JSON.stringify({
            user:player,
            message:'position',
            pos:{x:this.pos.x,y:this.pos.y},
            vel:{x:this.vel.x,y:this.vel.y},
            target:{x:this.target.x,y:this.target.y}
        }))
    }


}