import React from 'react';
import PropTypes from 'prop-types';
import EnvContext from './EnvContext';
import Text, { getText } from './Text';
import Dialog from './Dialog';
import FlexLayout from './layout/FlexLayout';
import ajax from './Ajax';
import { getJsonValue } from '../utils/JsonUtils';
import '../css/table.less';

class ToolButton extends React.Component {

	render() {
		if (this.props.privilege)
			return <a className={this.props.ButtonClass} onClick={(e)=>this.props.onClick(e)}><span className="ToolBarButton" ><Text name={this.props.label} /></span></a>
		else
			return null;
	}
}

ToolButton.propTypes = {
	label: PropTypes.string,
	privilege: PropTypes.bool
};

ToolButton.defaultProps = {
	label: null,
	privilege: true
};

class OperateButton extends React.Component {
	render() {
		let className = "OperateButton";
		if (this.props.className) 
			className += " " + this.props.className;
		return <a className={className} style={this.props.style} onClick={this.props.onClick}><Text name={this.props.label} /></a>
	}
}

class  OperateCheckButton extends React.Component {
	render() {
		let className = "OperateCheckButton";
		if (this.props.className) 
			className += " " + this.props.className;

		return (
			<input type="checkbox" className={className} style={this.props.style} onClick={this.props.onClick} value={this.props.value}/>
		)
	}
}

class ColumnModifyDialog extends Dialog {

	constructor(props) {
		super(props);
		this.state.avail = [];
		this.state.disp = [];
		this.view = props.view;
		this.onloadView = props.onloadView;
		this.leftRef = React.createRef();
		this.rightRef = React.createRef();
	}

	show(e) {
		ajax.get("ModifyTableColumn.do", { params: {viewname: this.view}})
		.then(response=>{
			let [disp, avail ] = response.data.object;
			this.setState({avail: avail, disp: disp});
			super.show(e);
		})
		.catch(ex=>{
			console.error(ex);
		});
	}

	addAll() {
		this.state.disp = this.state.disp.concat(this.state.avail);
		this.setState({avail:[], disp: this.state.disp});
	}

	add() {
		let i = this.leftRef.current.selectedIndex;
		if (i >= 0) {
			let e = this.state.avail[i];
			this.state.avail.splice(i, 1);
			this.state.disp.push(e);
			this.leftRef.current.selectedIndex = i;
			this.setState({avail: this.state.avail,  disp: this.state.disp});
		}
	}

	remove() {
		let i = this.rightRef.current.selectedIndex;
		if (i >= 0) {
			let e = this.state.disp[i];
			this.state.disp.splice(i, 1);
			this.state.avail.push(e);
			this.rightRef.current.selectedIndex = i;
			this.setState({avail: this.state.avail,  disp: this.state.disp});
		}
	}

	removeAll() {
		this.state.avail = this.state.avail.concat(this.state.disp);
		this.setState({avail:this.state.avail, disp: []});
	}

	getButtons() {
        return [<button onClick={(e)=>this.save(e)} key="save"><Text name="Save"/></button>, this.getCloseButton()];
    }

	save(e) {
		let DataView = this.context.getDataViewService;
		let p1 = DataView.getView(this.view);
		p1.then(view=>{
			let params = "?viewid=" + view.viewid;
			for (let f of this.state.avail) {
				params += "&fieldid=" + f.fieldid;
			}
			ajax.get("SaveTableColumn.do" + params)
			.then(response=>{
				if (response.data.error == false)
					this.onloadView(true);
			})
			.catch(ex=>{
				console.error(ex);
			});
		});
	}

	getContent() {
		let user = this.context.getUser();

		return (
			<div className="columnmodify-dialog">
				<table>
					<thead>
						<tr>
							<th className="left">Avail</th>
							<th className="op"></th>
							<th className="right">Display</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<select size="15" ref={this.leftRef}>
									{
										this.state.avail ? this.state.avail.map((field)=>{
											let label = "component." + this.view + "." + field.label;
											return <option value={field.fieldid} key={field.fieldid}>{getText("component."+this.view+"."+ field.label)}</option>;
										}) : null
									}
								</select>
							</td>
							<td >
								<input type="button" value=">>" onClick={(e)=>this.addAll()}/>
								<input type="button" value=" > " onClick={(e)=>this.add(e) } />
								<input type="button" value=" < " onClick={(e)=>this.remove(e) } />
								<input type="button" value="<<" onClick={(e)=>this.removeAll(e)}/>
							</td>
							<td>
								<select  size="15" ref={this.rightRef}>
								{
									this.state.disp ? this.state.disp.map((field)=>{
										let label = "component." + this.view + "." + field.label;
										return <option value={field.fieldid} key={field.fieldid}>{getText("component."+this.view+"."+ field.label)}</option>;
									}) : null
								}
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		);
	}
}
ColumnModifyDialog.contextType = EnvContext;

ColumnModifyDialog.propTypes = {
	view: PropTypes.string.isRequired
};

ColumnModifyDialog.defaultProps = {
	title : "Columns",
	modal: true,
	position : "center",
	width: 600
};


class Header extends React.Component {

	constructor(props) {
		super(props);

		this.onCreate = props.onCreate;
		this.addButton = props.addButton;

		this.onModifyColumns = props.onModifyColumns;
		this.modifyColumnButton = props.modifyColumnButton;

		this.toolBarButtons = props.toolBarButtons;
	}

    render() {
		return (
			<section>
				<div style={{float:"left"}}>
					{ this.addButton ? <ToolButton label="New" onClick={(e)=>this.onCreate(e)}/> : null }
					{
						this.toolBarButtons.map((button,idx)=>{
							return <ToolButton label={button.label} onClick={(e)=>button.onClick(e)} privilege={button.privilege} key={idx} />
						})
					}
				</div>
				<div style={{float:"right"}}>
					{ this.modifyColumnButton ? <ToolButton label="Columns" onClick={(e)=>this.onModifyColumns(e)}/> : null }
				</div>
			</section>
		);
	}
	
}

Header.propTypes = {
	toolBarButtons: PropTypes.array
};

Header.defaultProps = {
	toolBarButtons: []
};


class TableContent extends  React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		this.view = this.props.view;
		this.data = this.props.data.results ? this.props.data.results : this.props.data;
		this.viewname = this.props.viewname;
		this.viewlabel = "component."+this.viewname;

		this.onView = this.props.onView;
		this.onModify = this.props.onModify;
		this.onDelete = this.props.onDelete;
		this.onPickup = this.props.onPickup;
		this.onCheck = this.props.onCheck;

		this.viewButton = this.props.viewButton;
		this.modifyButton = this.props.modifyButton;
		this.deleteButton = this.props.deleteButton;
		this.pickupButton = this.props.pickupButton;
		this.checkButton = this.props.checkButton;
		this.extendOperateButtons = this.props.extendOperateButtons ? this.props.extendOperateButtons : [];

		this.PrimaryKeyColumnName = null;
		this.NameColumnName = null;

		for (let column of this.view.fields) {
			if (column.type == "pk") this.PrimaryKeyColumnName = column;
			if (column.type == "name") this.NameColumnName = column;
			if (this.PrimaryKeyColumnName && this.NameColumnName) break; 
		}

		if (this.PrimaryKeyColumnName == null) 
			throw "The View " + this.view.name + "'s PrimaryKey not define";
		if (this.NameColumnName == null)
			throw "The View " + this.view.name + "'s Name not define";


		return (
			<table >
				<caption><Text name={this.viewlabel} /> </caption>
				<colgroup>
					<col data-fieldname={this.PrimaryKeyColumnName.name}/>
					<col data-fieldname={this.NameColumnName.name} />
					{
						this.view.fields.map((column, idx)=>{
							return column.type != "pk" && column.type != "name" && column.displayIndex>0 ? <col data-fieldname={column.name} key={idx}/> : null;
						})
					}
					<col data-fieldname="Operate"/>
				</colgroup>
				<thead>
					<tr>
						<th><Text name="NO" /></th>
						<th><Text name={"component." + this.view.label + "." + this.NameColumnName.label} /></th>
						{
							this.view.fields.map((column, idx)=>{
								return column.type != "pk" && column.type != "name" && column.displayIndex>0 ? <th key={idx}><Text name={"component." + this.view.label + "."+ column.label}/></th> : null;
							})
						}
						<th><Text name="Operate" /></th>
					</tr>
				</thead>
				<tbody>
					{
						this.data.map((context, index)=> {
							return (
								<tr key={index}>
									<td>{index+1}</td>
									<td>{ this.viewButton ? <a className="name" onClick={(e)=>this.onView(e, context[this.PrimaryKeyColumnName.name], context)}> {context[this.NameColumnName.name] } </a> : context[this.NameColumnName.name] }</td>
									{
										this.view.fields.map((column,idx)=>{
											if (column.type == "pk" || column.type=="name" || column.displayIndex == 0)
												return null;
											
											let val = null;
											switch (column.type) {
												case "pk":
												case "name":  break;
												case "enum":  {
													let v = context[column.name];
													val = getText("enum." + column.enumname + "." + v);
													break; 
												}
												case "string": {
													if (column.ognl == null) 
														val = context[column.name];
													else 
														val = getJsonValue(context, column.ognl);

													if (val == null) 
														val = "";
													break;
												}
												case "date": {
													val = context[column.name] ? context[column.name].substring(0, context[column.name].indexOf("T")) : null;
													break; 
												}
												case "datetime": {
													
												}
												default: val = context[column.name];
														break ;
											}

											return <td key={idx}>{val}</td>
										})
									}
									<td>
										{ this.modifyButton ? <OperateButton label="Modify" className="ModifyButton" onClick={(e)=>this.onModify(e, context[this.PrimaryKeyColumnName.name], context)} /> : null }
										{ this.deleteButton ? <OperateButton label="Delete" className="DeleteButton" onClick={(e)=>this.onDelete(e, context[this.PrimaryKeyColumnName.name], context)} /> : null }
										{ this.pickupButton ? <OperateButton label="Pickup" className="PickupButton" onClick={(e)=>this.onPickup(e, context[this.PrimaryKeyColumnName.name], context[this.NameColumnName.name], context)} /> : null }
										{ this.checkButton ?  <OperateCheckButton value={context[this.PrimaryKeyColumnName.name]} onClick={(e)=>this.onCheck(e, context[this.PrimaryKeyColumnName.name], context[this.NameColumnName.name], context)} /> : null }
										{
											this.extendOperateButtons.map((button, idx)=> {
												return <OperateButton key={idx} label={button.label} privilege={button.privilege} style={button.style} onClick={(e)=>button.onClick(e, context[this.PrimaryKeyColumnName.name], context)} />
											})
										}
									</td>
								</tr>
							)
						})
					} 
			</tbody>
			<tfoot>
				<tr>
					<td><span style={{display:"none"}}>Copyright By vcshcn '_'</span></td>
				</tr>
			</tfoot>
		</table>
		);
	}
}

TableContent.contextType = EnvContext;

class Footer extends React.Component {

	constructor(props) {
		super(props);
		this.page = props.data ? props.data : null;
		this.go = props.go;
	}

	first(e) {
		this.go(e, this.page.firstPage);
	}

	prev(e) {
		this.go(e, this.page.prevPage);
	}

	next(e) {
		this.go(e, this.page.nextPage);
	}

	last(e) {
		this.go(e, this.page.lastPage);
	}

	render() {
		let r = <span>&nbsp;</span>;
		if (this.page.pageNO && this.page.pageCount) {
			r = [];
			r.push(<a onClick={(e)=>this.first(e)} key="first"><Text name="First" /></a>);
			r.push(" ");
			r.push(<a onClick={(e)=>this.prev(e)} key="prev"><Text name="Prev" /></a>);
			r.push(" ");
			r.push(this.page.pageNO + "/" + this.page.pageCount);
			r.push(" ");
			r.push(<a onClick={(e)=>this.next(e)} key="next"><Text name="Next" /></a>);
			r.push(" ");
			r.push(<a onClick={(e)=>this.last(e)} key="last"><Text name="Last" /></a>);
			r.push(" ");
		}

		return (
			<footer>
				{ r }
			</footer>
		);
	}
}

export default class Table extends React.Component {

    constructor(props) {
		super(props);
		this.columnsDialogRef = React.createRef();

		this.viewname = this.props.view;
		this.url = this.props.url ? this.props.url : "List"+this.viewname+".do";
		this.params = this.props.params ? this.props.params : {};
		this.debug = this.props.debug;
		this.extendOperateButtons = this.props.extendOperateButtons;
		this.toolBarButtons = this.props.toolBarButtons;

		this.addButton = props.addButton;
		this.modifyColumnButton = props.modifyColumnButton;
		this.checkButton = props.checkButton;
		this.viewButton = props.viewButton;
		this.modifyButton = this.checkButton ? false : props.modifyButton;
		this.deleteButton = this.checkButton ? false : props.deleteButton;
		this.pickupButton = this.checkButton ? false : props.pickupButton;

		this.onAdd  = props.onAdd;
		this.onModifyColumn = props.onModifyColumn;
		this.onView  = props.onView;
		this.onModify = props.onModify;
		this.onDelete = props.onDelete;
		this.onPickup = props.onPickup;
		this.onCheck = props.onCheck;
	
		this.state = {
			data: null,
			view: null,
			query: null
		}
	}
	
	loadViewAndData(query, reload = false) {
		let DataView = this.context.getDataViewService;
		let p1 = DataView.getView(this.viewname, reload);
		let p2 = ajax.post(this.url,  query);

		Promise.all([p1, p2]).then((response)=>{
			this.setState({view: response[0], data: response[1].data.object, query: query});
		})
		.catch((ex)=>{
			console.error(ex);
		});
	}

	loadView(reload = false) {
		let DataView = this.context.getDataViewService;
		let p1 = DataView.getView(this.viewname, reload);
		p1.then(response=> {
			this.setState({view: response});
		})
		.catch(ex=>{
			console.error(ex);
		});
	}

	loadData(query) {
		ajax.post(this.url, query)
		.then((response)=>{
			this.setState({data: response.data.object, query: query});
		})
		.catch((ex)=>{
			console.error(ex);
		});

	}

	componentDidMount() {
		this.loadViewAndData(this.params);
	}

	create(e) {
		if (this.onAdd) {
			e.preventDefault();
			(e)=>this.onAdd(e);
		}
		else
			this.props.history.push("/Create" + this.viewname)
	}

	view(e, id, obj) {
		if (this.onView) {
			e.preventDefault();
			(e)=>this.onView(e);
		}
		else
			this.props.history.push("/View" + this.viewname + "/" + id)
	}

	modify(e, id, obj) {
		if (this.onModify) {
	        e.preventDefault();
			(e)=>this.onModify(e);
		}
		else
			this.props.history.push("/Modify" + this.viewname + "/" + id)
	}

	delete(e, id, obj) {
		if (this.onDelete) {
			e.preventDefault();
			(e)=>this.onDelete(e);
		}
	}

	pickup(e, id, name, obj) {
		if (this.onPickup != null) 
			this.onPickup(e, id, name, obj);
	}

	check(e, id, name, obj) {
		if (this.onCheck != null) 
			this.onCheck(e, id, name, obj);
	}

	modifyColumns(e) {
		this.columnsDialogRef.current.show(e);
	}

	go(e, pageNO) {
        e.preventDefault();
		this.params["pageNO"] = pageNO;
		this.loadData(this.params);
	}

    render() {
		let r = null;
		if (this.state.view && this.state.data ) {
			if (this.debug) {
				console.log(this.state.view);
				console.log(this.state.data);
			}

			r = (
				<div className="DataTable">
					<Header view = {this.state.view} 
							viewname = {this.viewname} 
							debug = {this.debug} 

							onCreate = {(e)=>this.create(e)}
							addButton = {this.addButton}
							onModifyColumns={(e)=>this.modifyColumns(e)} 
							modifyColumnButton = {this.modifyColumnButton}
							toolBarButtons = {this.toolBarButtons}
					/>

					<TableContent 	view = {this.state.view} 
									data = {this.state.data} 
									viewname = {this.viewname} 
									debug = {this.debug} 

									onView = {(e,id,obj)=>this.view(e,id,obj)} 
									viewButton = {this.viewButton}
									onModify = {(e,id,obj)=>this.modify(e,id,obj)}
									modifyButton = {this.modifyButton}
									onDelete = {(e,id,obj)=>this.delete(e,id,obj)}
									deleteButton = {this.deleteButton}
									onPickup = {(e,id, name,obj)=>this.pickup(e,id,name,obj)}
									pickupButton = {this.pickupButton}
									onCheck = {(e,id, name,obj)=>this.check(e,id,name,obj)}
									checkButton = {this.checkButton}

									extendOperateButtons={this.extendOperateButtons}
					/>

					<Footer view={this.state.view} 
							data={this.state.data} 
							debug={this.debug} 
							go={ (n)=>this.go(n) } 
					/>

					<ColumnModifyDialog ref={this.columnsDialogRef} view={this.viewname} onloadView={()=>this.loadView(true)}/>
				</div>
			);
		}
		else {
			r = <div  className="DataTable"></div>;
		}

        return r;
    }
}

Table.contextType = EnvContext;

Table.propTypes = {
	view: PropTypes.string.isRequired,
	url: PropTypes.string,
	params: PropTypes.object,
	debug : PropTypes.bool,
	extendOperateButtons: PropTypes.array,
	toolBarButtons:  PropTypes.array,

	addButton: PropTypes.bool,
	modifyColumnButton: PropTypes.bool,
	viewButton: PropTypes.bool,
	modifyButton: PropTypes.bool,
	deleteButton: PropTypes.bool,
	pickupButton: PropTypes.bool,
	checkButton: PropTypes.bool,

	onAdd : PropTypes.func,
	onModifyColumn : PropTypes.func,
	onView : PropTypes.func,
	onModify : PropTypes.func,
	onDelete : PropTypes.func,
	onPickup : PropTypes.func,
	onCheck: PropTypes.func,
};

Table.defaultProps = {
	debug : false,

	addButton: true,
	modifyColumnButton: true,
	viewButton: true,
	modifyButton: true,
	deleteButton: true,
	pickupButton: false,
	checkButton: false,

};
