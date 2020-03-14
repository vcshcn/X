export default class StorageService {

    constructor(impl) {
        if (impl && typeof impl != undefined) 
            this.storageImpl = impl;
        else
            this.storageImpl = {};
    }

    getItem(key) {
        if (this.storageImpl.hasOwnProperty("getItem") ) {
            return this.storageImpl.getItem(key);
        }
        return this.storageImpl[key];
    }

    setItem(key, val) {
        if (this.storageImpl.hasOwnProperty("setItem") ) {
            return this.storageImpl.setItem(key, val);
        }
        return this.storageImpl[key] = val;
    }

    removeItem(key) {
        if (this.storageImpl.hasOwnProperty("removeItem") ) {
            return this.storageImpl.removeItem(key);
        }
        return delete this.storageImpl[key];
    }

    clear() {
        if (this.storageImpl.hasOwnProperty("clear") ) {
            return this.storageImpl.clear();
        }
        return this.storageImpl= {};
    }

    setTempItem(key, val) {
        return this.setItem("tmp." + key, val);
    }

    removeTempItem(key) {
        return this.removeItem("tmp."+key);
    }

    clearTemp() {
        let n = this.storageImpl.length;
        for (let i=0; i<n; i++) {
            let key = this.storageImpl.key(i);
            if (key.startsWith("tmp.")) {
                this.removeItem(key);
            }
        }
    }

}