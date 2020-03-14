import React from 'react';
import '../../css/Home.module.less';

export default class Home extends React.Component {

    render() {

        return (
            <div>
                <div className="section1" style={{width:"400px",float:"left"}}>
                    <div>Task</div>
                    <ul>
                            <li>
                                <div style={{width:"300px"}}></div>
                                <div style={{width:"200px"}}></div>
                            </li>
                    </ul>
                </div>
                <div className="section1" style={{width:"400px",float:"left"}}>
                    <div>NewsType.Notice</div>
                    <ul>
                            <li>
                                <div style={{width:"300px"}}></div>
                                <div style={{width:"200px"}}></div>
                            </li>
                    </ul>
                </div>
                <div className="section1" style={{width:"400px",float:"left"}}>
                    <div>NewsType.News</div>
                    <ul>
                            <li>
                                <div style={{width:"300px"}}></div>
                                <div style={{width:"200px"}}></div>
                            </li>
                    </ul>
                </div>
                <div style={{clear:'both'}}></div>
            </div>
        );
    }

    render1() {
        return (
            <div style={{margin:"2px", border: "2px solid #07c"}}>
                <div style={{fontFamily:"arial",background:"#07c",height:"45px",lineHeight:"42px",color:"white",textAlign:"center"}}><span style={{fontSize:"large"}}>banner</span></div>
                <div style={{paddingBottom: "12px",margin:0 }}>
                    <ul style={{listStyle:"none",margin: 0,display:"block",padding:"0 12px"}}>
                        <li style={{color:"#07c",textDecoration:"none",margin:0,padding: "12px 0 0"}}>
                                titletitle <br/>
                                <div style={{color:"#666",fontWeight:700}}>abcdefghijkl</div>
                                <div style={{color:"#666"}}>dfsdfdsf</div>
                                <span style={{fontSize:"12px",lineHeight:1.4,margin: "2px 2px 2px 0",border:"1px solid #e4edf4",display:"inline-block",background:"#e4edf4",backgroundColor: "#a8cbff", color:"#344969",borderColor:"#a8cbff"}}>11111</span>
                                <span style={{background:"#e4edf4",color:"#3e6d8e",margin:"2px 2px 2px 0",padding:".25em .5em",fontSize:"12px", lineHeight:1.4,display:"inline-block",border:"1px solid #e4edf4"}}>2222</span>
                        </li>
                        <li>adfsdf</li>
                        <li>adfsdf</li>
                        <li>adfsdf</li>
                        <li>adfsdf</li>
                    </ul>
                </div>
            </div>
        );    
    }
 
}
