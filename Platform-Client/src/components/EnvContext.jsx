import React from 'react';

const default_language = "en";
const default_country = "US";
const default_title = "Admin Console";
const default_date_format = "yyyy-MM-dd";
const default_datetime_format = "yyyy-MM-dd HH:MM:ss";

const Defaults = {
    language :  default_language,
    country :   default_country,
    locale:     default_language + "_" + default_country,
    title :     default_title,
    dateformat : default_date_format,
    datetimeformat : default_datetime_format
}


// export application object for pure function
let application;

export function registerApplication(app) {
    application = app
}
export function getApplication() {
    return application;
}

// export ctx for component
const EnvContext = React.createContext();

export { Defaults };
export default EnvContext;
