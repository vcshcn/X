import ajax from './Ajax';

export default class DataViewService {

    constructor(storage) {
        if (storage)
            this.storage = storage;
        else 
            this.storage = {};
    }

    async loadView(viewname) {
        try {
            let response = await ajax.get("ViewDataView.do", { params: {viewname: viewname}});
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

    async getView(viewame, reload=false) {
        let view = null;
        let s = reload ? null : this.storage.getItem("DataView." + viewame); 

        if (s != null) {
            view = JSON.parse(s);
        }

        if (view == null) {
            view = await this.loadView(viewame);
            this.storage.setItem("DataView."+viewame, JSON.stringify(view) );
        }

        return view;
    }
 

}
