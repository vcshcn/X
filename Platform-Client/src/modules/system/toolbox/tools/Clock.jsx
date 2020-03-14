import React from 'react';

const PI = Math.PI;
const size = screen.height / 2 - 200 ;
const R = size /2 - 30;
const width = size   ; //320;
const height = size  ;//320;
const center = { x: width / 2, y : height /2 -10};
const background= document.createElement("canvas");

export default class Clock extends React.Component {

    constructor(props) {
        super(props);
        this.canvas = React.createRef();
    }

    componentDidMount() {
        let canvas = this.canvas.current;
        canvas.width = size;
        canvas.height = size;

        let ctx = canvas.getContext("2d");
        ctx.translate(center.x, center.y );
        ctx.beginPath();
        ctx.fillStyle =  'white';;
        ctx.arc(0, 0, R+10, 0, 2*PI, false );
        ctx.lineWidth = 1;
        ctx.strokeStyle = '#f6a828';
        ctx.stroke();
        ctx.fill();
        ctx.lineWidth = 2;
        ctx.arc(0, 0, R+14, 0, 2*PI, false );
        ctx.stroke();
        ctx.closePath();
        
        this.drawDot(ctx, 0,0, 10);
        
        let d = 0;
        for (let i=0; i<13; i++) {
            
            d = i * (2 * PI / 12) ;
            let p = this.polar2angle(R, d);
            this.drawDot(ctx, p.x, p.y, 6);
            
            for (let j=0; j<5; j++) {
                d += ( 2 * PI / 12 / 5);
                let p = this.polar2angle(R, d);
                this.drawDot(ctx, p.x, p.y, 2);
            }
        }
            
        background.width = canvas.width;
        background.height = canvas.height;
        background.getContext("2d").drawImage(canvas, 0,0);

        this.__runClock();

    }

    __runClock() {
        if (! (this.canvas && this.canvas.current))
            return ;
        let canvas = this.canvas.current;

        let now = new Date();
        now.setTime(now.getTime() + 1000);
		
		let seconds = now.getSeconds();
		let secondsPosition = seconds * ( 2 * PI / 60 ) - 2 * PI / 4;
		let minute = now.getMinutes();
		let minutePosition = minute * ( 2 * PI / 60 ) - 2 * PI / 4;
		let hour = now.getHours();
		let hourPosition =  ( (hour > 11 ? hour-12 : hour) * ( 2 * PI / 12 )) - 2 * PI / 4 + minute * ( 2 * PI / 60 ) / 12 ;

		let ctx = canvas.getContext("2d");
		ctx.translate(-center.x, -center.y);
		ctx.drawImage(background,0,0);
		ctx.translate(center.x, center.y );

		let p = this.polar2angle(R, secondsPosition);
		this.drawLine(ctx, 0, 0, p.x, p.y, 1);
		
		p = this.polar2angle(R - 20, minutePosition);
		this.drawLine(ctx, 0, 0, p.x, p.y, 2);
		
		p = this.polar2angle(R - 40, hourPosition);
		this.drawLine(ctx, 0, 0, p.x, p.y, 2);
		
		ctx.font = '20pt Arial';
		var text = (hour < 12 ? "AM" : "PM") + " (" + now.FormatDate("yyyy-M-d") + ")"
		var metrics = ctx.measureText(text);
		var w = metrics.width;
		ctx.fillStyle = 'black';
		ctx.clearRect(-w/2, 160, w, height /2 + 10 );
		ctx.fillText(text, -w/2, height /2 +10);
		
		window.setTimeout( ()=>{
            this.__runClock();
        },1000); 
    }

    drawLine(ctx, x1, y1, x2, y2, lineWidth) {
        ctx.lineWidth = lineWidth;
        ctx.strokeStyle = '#f6a828';
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2);
        ctx.stroke();
    }

    drawDot(ctx, x, y, r) {
        ctx.beginPath();
        ctx.fillStyle =  'lightblue';;
        ctx.arc(x, y, r, 0, 2*PI, false );
        ctx.lineWidth = 1;
        ctx.fill();
        ctx.closePath();
    }
    
    polar2angle(radius, radian) {
        let r = {};
        r.x = radius * Math.cos(radian);
        r.y = radius * Math.sin(radian);
        return r;
    }

    render() {
        return (
            <div style={{textAlign: "center"}} >
                <canvas width="350px" height="350px" id="canvas" ref={this.canvas}>
                    Please chrome , firefox
                </canvas>
            </div>
        );
    }
}

