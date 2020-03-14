import ajax from './Ajax';

export default class EnumTableService {

    constructor(storage) {
        if (storage)
            this.storage = storage;
        else 
            this.storage = {};
    }


    async load(table) {
        try {
            let response = await ajax.get(`ListAll${table}.do`);
            let data = response.data;
            if (data.error) {
                throw response;
            }
            return data.object;
        }
        catch (ex) {
            throw ex;
        }
    }

    async getItems(table) {
        let node = null;
        let s = this.storage.getItem("Enum." + table);
        if (s != null) {
            node = JSON.parse(s);
        }
        if (node == null) {
            node = await this.load(table);
            if (node != null) {
                this.storage.setItem("Enum."+table, JSON.stringify(node) );
            }
        }
        return node;
    }

}
