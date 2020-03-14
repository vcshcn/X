import Orange from './themes/orange';
import Dark from './themes/dark';

export default class ThemeService {

    constructor(user) {
        this.user = user;

        this.themes = new Map();
        this.themes.set("orange", Orange);
        this.themes.set("dark", Dark);

        this.current = this.getDefaultTheme();
    }

    getSupportThemes() {
        return new Array.from(this.themes.keys);
    }

    getDefaultTheme() {
        return "orange";
    }

    getCurrentTheme() {
        return this.current;
    }

    isSupportTheme(theme) {
        return this.themes.get(theme) != undefined;
    }

    applyTheme(theme) {
        if (this.isSupportTheme(theme) == false)
            throw "Not Support Theme with " + theme + ".";

        let json = this.themes.get(theme);
        if (typeof json == "object") {
            this.executeTheme(json);
            this.current = theme;
        }
        else
            throw exec + " format error.";
        return true;
    }

    executeTheme(json) {
        for (let key in json) {
            let value = json[key];
            document.body.style.setProperty(key, value);
        }
    }
}

