export default class BrowserService {

    static getCountry() {
        return null;
    }
    
    static getLanguage() {
        return null;
    }

    static getLocale() {
        return null;
    }
    
    static getTimezone() {
        return null;
    }
   
    static getOSType() {
        let OSName = "Unknown";
        if (window.navigator.userAgent.indexOf("Windows")!= -1) OSName="Windows";
        if (window.navigator.userAgent.indexOf("Mac")    != -1) OSName="Mac/iOS";
        if (window.navigator.userAgent.indexOf("X11")    != -1) OSName="UNIX";
        if (window.navigator.userAgent.indexOf("Linux")  != -1) OSName="Linux";
        return OSName;
    }
}

