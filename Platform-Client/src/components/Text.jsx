import React from 'react';
import PropTypes from 'prop-types';
import EnvContext, { Defaults, getApplication } from './EnvContext';
import BrowserService from './BrowserService';
import en_US from '../resources/resource_en_US'
import zh_CN from '../resources/resource_zh_CN'
import zh_TW from '../resources/resource_zh_TW'

class ResourceBundle {

    constructor(res, lang, country) {
        this.resource = res;
        this.lang = lang;
        this.country = country;
    }

    getText(name) {
        let names = name.split(".");
        let s = "";
        let r = this.resource;

        for (let i=0; i<names.length; i++) {
            let name = names[i];

            if (typeof(r) == "undefined") {
                console.warn(names.join(".") + " not define");
                return null;
            }

            r = r[name];


            if (typeof(r) == 'object' && i == names.length-1) {
                return r["_"];
            }

        }
        return r;
    }

    getLang() {
        return this.lang;
    }

    getCountry() {
        return this.country;
    }
}

const resources = [new ResourceBundle(en_US, 'en', 'US' ), new ResourceBundle(zh_CN, 'zh', 'CN'), new ResourceBundle(zh_TW, 'zh', 'TW') ];

function findResource(lang, country) {
    for (let resource of resources) {
        if (lang == null && country == null) {
            return resource;
        }
        if (lang && country == null && resource.getLang() == lang) {
            return resource;
        }
        if (lang == null && country && resource.getCountry() == country ) {
            return resource
        }
        if (resource.getLang() == lang && resource.getCountry() == country) {
            return  resource;
        }
    }
    return null;
}


function getI18nText2(names, language, country) {
    if (names == null) {
        return null;
    }
    //
    if (language == null && country == null) {
        language = Defaults.language;
        country = Defaults.country;
    }
    // 
    let resource = findResource(language, country);
    if (resource == null) {
        throw "Can't Find Resource at Locale with " + language + "_" + country;
    }

    let s = resource.getText(names);
    if (s != null) 
        return s;
    return names;

}


function getI18nText1(names, locale) {
    if (locale && locale.indexOf("_") >=0) {
        let r = locale.split("_");
        let language = r[0].trim();
        let country = r[1].trim();
        return getI18nText2(names, language!=""?language:null, country!=""?country:null);
    }
    if (locale) {
        return getI18nText2(names, locale, null);
    }
    let text = getI18nText2(names, null, null);
    return text ? text : names;

}

function getText(names, loc) {

    let locale = null;

    if (loc) {
        locale = loc;
    }
    else {
        let user = getApplication().getUser();
        if (user && user.locale)
            locale = user.locale.code;
    }
   
    if (typeof locale == undefined || locale == null || locale == "") {
        locale = BrowserService.getLocale();

        if (!locale) 
            locale = Defaults.locale;
        
        if (!locale) 
            locale = "en_US";
    }

    return getI18nText1(names, locale);
}

export { getText };


export default class Text extends React.Component {

    constructor(props) {
        super(props);
    }

    getText(key) {
        let text = null;
        if (this.props.locale) 
            text = getText(key,  this.props.locale);
        else
            text = getText(key,  this.context.getUser() && this.context.getUser().locale ? this.context.getUser().locale.code : null);

        return text ? text : key;
    }

    render() {
        if (Array.isArray(this.props.name) == true) {
            let s = "";
            this.props.name.map(k=>{
                s += this.getText(k);
            });
            return s;
        }
        return this.getText(this.props.name);
    }
}

Text.contextType = EnvContext;

Text.propTypes = {
    name : PropTypes.string.isRequired,
    locale : PropTypes.string
};

Text.defaultProps = {
    locale: null
};
