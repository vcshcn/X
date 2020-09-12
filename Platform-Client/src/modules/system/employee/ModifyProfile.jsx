import React from 'react';
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";
import {Tabs, Tab, Text, Panel, ErrorDialog} from '../../../components/Controls';
import { DateInput, Select } from '../../../components/Forms';
import ajax from '../../../components/Ajax';
import { getText } from '../../../components/Text';
import EnvContext from '../../../components/EnvContext';
import View, {Field} from '../../view/ComponentView';

class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.user = props.user;

        this.nicknameKey = React.createRef();
        this.telKey = React.createRef();
        this.mobileKey = React.createRef();
        this.birthdayKey = React.createRef();
        this.emailKey = React.createRef();
        this.homepageKey = React.createRef();
        this.zipcodeKey = React.createRef();
        this.addressKey = React.createRef();
        this.countryKey = React.createRef();
        this.localeKey = React.createRef();
        this.timezoneKey = React.createRef();

    }

    SaveProfile(e) {
        e.preventDefault();
        let params = {
            nickname : this.nicknameKey.current.value,
            tel : this.telKey.current.value,
            mobile : this.mobileKey.current.value,
            birthday : this.birthdayKey.current.value,
            email : this.emailKey.current.value,
            homepage : this.homepageKey.current.value,
            zipcode : this.zipcodeKey.current.value,
            address : this.addressKey.current.value,
            countryid:  this.countryKey.current.value ? parseInt(this.countryKey.current.value) : null,
            localeid: this.localeKey.current.value ? parseInt(this.localeKey.current.value) : null,
            timezoneid:  this.timezoneKey.current.value ? parseInt(this.timezoneKey.current.value) : null
        };

        ajax.post("SaveProfileInformation.do", params)
        .then((response)=>{
            if (response.data.error == false) {
                ((u)=>this.context.setUser(u))(response.data.object);
                this.props.history.push("/ViewProfile/Profile");
            }
        })
        .catch(ex=>{
            console.error(ex);
        });
    }

    getPhotoRender() {
        return (
            <div>
                <img alt="my photo" src="ViewProfilePhoto.do" width="128px" height="128px" />
                <p></p>
                <div style={{textAlign:"center"}}> <a className="linkbutton">Not i</a> </div>
            </div>
        );
    }

    getProfileRender() {
        return (
            <View view="Employee">
                <form autoComplete="off" onSubmit={(e)=>this.SaveProfile(e)}>
                    <Panel layout="grid" cols="3">
                        <Field name="name">{this.user.name}</Field>
                        <Field name="gender">{this.user.gender}</Field>
                        <Field name="role">{this.user.role ? this.user.role.name : null}</Field>
                        <Field name="org" cols="3">{this.user.department ? this.user.department.org.name : null}</Field>
                        <Field name="department" >{this.user.department ? this.user.department.name : null}</Field>
                    </Panel>
                    <br/>
                    <Panel layout="grid" cols="3">
                        <Field name="nickname" required={true} >
                            <input type="text" name="nickname" ref={this.nicknameKey} defaultValue={this.user.nickname} required={true} />
                        </Field>
                        <Field name="tel">
                            <input type="text" name="tel" ref={this.telKey} defaultValue={this.user.tel }/>
                        </Field>
                        <Field name="mobile">
                            <input type="text" name="mobile" ref={this.mobileKey} defaultValue={this.user.mobile}/>
                        </Field>

                        <Field name="birthday">
                            <DateInput name="birthday" value={this.user.birthday} ref={this.birthdayKey} />
                        </Field>
                        <Field name="email">
                            <input type="email" ref={this.emailKey} name="email" defaultValue={this.user.email} />
                        </Field>
                        <Field name="homepage">
                            <input type="url" ref={this.homepageKey} name="homepage" defaultValue={this.user.homepage} />
                        </Field>

                        <Field name="zipcode">
                            <input  type="text" name="zipcode" ref={this.zipcodeKey} defaultValue={this.user.zipcode}/>
                        </Field>
                        <Field name="address" cols="3">
                            <input type="text" name="address" ref={this.addressKey} defaultValue={this.user.address}/>
                        </Field>

                        <Field name="country">
                            <Select name="Country" ref={this.countryKey} valueKey="countryid" labelKey="name" value={this.user.country ? this.user.country.countryid : null}/>
                        </Field>
                        <Field name="language">
                            <Select name="Locale" ref={this.localeKey} valueKey="localeid" labelKey="name" value={this.user.locale ? this.user.locale.localeid : null} />
                        </Field>
                        <Field name="timezone">
                            <Select name="TimeZone" ref={this.timezoneKey} valueKey="timezoneid" labelKey="name" value={this.user.timezone ? this.user.timezone.timezoneid : null}/>
 <select name="timezone" class="form-control" id="id_timezone">
  <option value="Pacific/Pago_Pago">UTC -11:00: 美属萨摩亚时间</option>

  <option value="Pacific/Niue">UTC -11:00: 纽埃时间</option>

  <option value="Pacific/Midway">UTC -11:00: 美国本土外小岛屿（中途岛）时间</option>

  <option value="Pacific/Rarotonga">UTC -10:00: 库克群岛时间</option>

  <option value="Pacific/Tahiti">UTC -10:00: 法属波利尼西亚时间</option>

  <option value="America/Adak">UTC -10:00: 世界（埃达克）时间</option>

  <option value="Pacific/Honolulu">UTC -10:00: 美国（檀香山）时间</option>

  <option value="US/Hawaii">UTC -10:00: 美国（檀香山）时间</option>

  <option value="Pacific/Marquesas">UTC -09:30: 世界（马克萨斯）时间</option>

  <option value="Pacific/Gambier">UTC -09:00: 世界（甘比尔）时间</option>

  <option value="America/Anchorage">UTC -09:00: 美国（安克雷奇）时间</option>

  <option value="America/Juneau">UTC -09:00: 美国（朱诺）时间</option>

  <option value="America/Sitka">UTC -09:00: 美国（锡特卡）时间</option>

  <option value="America/Metlakatla">UTC -09:00: 世界（梅特拉卡特拉）时间</option>

  <option value="America/Yakutat">UTC -09:00: 美国（亚库塔特）时间</option>

  <option value="America/Nome">UTC -09:00: 美国（诺姆）时间</option>

  <option value="US/Alaska">UTC -09:00: 美国（安克雷奇）时间</option>

  <option value="America/Vancouver">UTC -08:00: 加拿大（温哥华）时间</option>

  <option value="America/Whitehorse">UTC -08:00: 加拿大（怀特霍斯）时间</option>

  <option value="America/Dawson">UTC -08:00: 加拿大（道森）时间</option>

  <option value="America/Tijuana">UTC -08:00: 墨西哥（蒂华纳）时间</option>

  <option value="Pacific/Pitcairn">UTC -08:00: 世界（皮特凯恩）时间</option>

  <option value="America/Los_Angeles">UTC -08:00: 美国（洛杉矶）时间</option>

  <option value="US/Pacific">UTC -08:00: 美国（洛杉矶）时间</option>

  <option value="America/Edmonton">UTC -07:00: 加拿大（埃德蒙顿）时间</option>

  <option value="America/Cambridge_Bay">UTC -07:00: 加拿大（剑桥湾）时间</option>

  <option value="America/Yellowknife">UTC -07:00: 加拿大（耶洛奈夫）时间</option>

  <option value="America/Inuvik">UTC -07:00: 加拿大（伊努维克）时间</option>

  <option value="America/Creston">UTC -07:00: 加拿大（克雷斯顿）时间</option>

  <option value="America/Dawson_Creek">UTC -07:00: 加拿大（道森克里克）时间</option>

  <option value="America/Fort_Nelson">UTC -07:00: 未知地区（Fort Nelson）时间</option>

  <option value="America/Mazatlan">UTC -07:00: 墨西哥（马萨特兰）时间</option>

  <option value="America/Chihuahua">UTC -07:00: 墨西哥（奇瓦瓦）时间</option>

  <option value="America/Ojinaga">UTC -07:00: 墨西哥（奥希纳加）时间</option>

  <option value="America/Hermosillo">UTC -07:00: 墨西哥（埃莫西约）时间</option>

  <option value="America/Denver">UTC -07:00: 美国（丹佛）时间</option>

  <option value="America/Boise">UTC -07:00: 美国（博伊西）时间</option>

  <option value="America/Phoenix">UTC -07:00: 美国（凤凰城）时间</option>

  <option value="US/Arizona">UTC -07:00: 美国（凤凰城）时间</option>

  <option value="US/Mountain">UTC -07:00: 美国（丹佛）时间</option>

  <option value="America/Belize">UTC -06:00: 伯利兹时间</option>

  <option value="America/Winnipeg">UTC -06:00: 加拿大（温尼伯）时间</option>

  <option value="America/Rainy_River">UTC -06:00: 加拿大（雷尼河）时间</option>

  <option value="America/Resolute">UTC -06:00: 加拿大（雷索卢特）时间</option>

  <option value="America/Rankin_Inlet">UTC -06:00: 加拿大（兰今湾）时间</option>

  <option value="America/Regina">UTC -06:00: 加拿大（里贾纳）时间</option>

  <option value="America/Swift_Current">UTC -06:00: 加拿大（斯威夫特卡伦特）时间</option>

  <option value="America/Costa_Rica">UTC -06:00: 哥斯达黎加时间</option>

  <option value="Pacific/Galapagos">UTC -06:00: 厄瓜多尔（加拉帕戈斯）时间</option>

  <option value="America/Guatemala">UTC -06:00: 危地马拉时间</option>

  <option value="America/Tegucigalpa">UTC -06:00: 洪都拉斯时间</option>

  <option value="America/Mexico_City">UTC -06:00: 墨西哥（墨西哥城）时间</option>

  <option value="America/Merida">UTC -06:00: 墨西哥（梅里达）时间</option>

  <option value="America/Monterrey">UTC -06:00: 墨西哥（蒙特雷）时间</option>

  <option value="America/Matamoros">UTC -06:00: 墨西哥（马塔莫罗斯）时间</option>

  <option value="America/Bahia_Banderas">UTC -06:00: 墨西哥（巴伊亚班德拉斯）时间</option>

  <option value="America/Managua">UTC -06:00: 尼加拉瓜时间</option>

  <option value="America/El_Salvador">UTC -06:00: 萨尔瓦多时间</option>

  <option value="America/Chicago">UTC -06:00: 美国（芝加哥）时间</option>

  <option value="America/Indiana/Tell_City">UTC -06:00: 美国（印第安纳州特尔城）时间</option>

  <option value="America/Indiana/Knox">UTC -06:00: 美国（印第安纳州诺克斯）时间</option>

  <option value="America/Menominee">UTC -06:00: 美国（梅诺米尼）时间</option>

  <option value="America/North_Dakota/Center">UTC -06:00: 美国（北达科他州申特）时间</option>

  <option value="America/North_Dakota/New_Salem">UTC -06:00: 美国（北达科他州新塞勒姆）时间</option>

  <option value="America/North_Dakota/Beulah">UTC -06:00: 美国（北达科他州比尤拉）时间</option>

  <option value="US/Central">UTC -06:00: 美国（芝加哥）时间</option>

  <option value="America/Eirunepe">UTC -05:00: 巴西（依伦尼贝）时间</option>

  <option value="America/Rio_Branco">UTC -05:00: 巴西（里奥布郎库）时间</option>

  <option value="America/Nassau">UTC -05:00: 巴哈马时间</option>

  <option value="America/Toronto">UTC -05:00: 加拿大（多伦多）时间</option>

  <option value="America/Nipigon">UTC -05:00: 加拿大（尼皮贡）时间</option>

  <option value="America/Thunder_Bay">UTC -05:00: 加拿大（桑德贝）时间</option>

  <option value="America/Iqaluit">UTC -05:00: 加拿大（伊魁特）时间</option>

  <option value="America/Pangnirtung">UTC -05:00: 加拿大（旁涅唐）时间</option>

  <option value="America/Atikokan">UTC -05:00: 加拿大（阿蒂科肯）时间</option>

  <option value="Pacific/Easter">UTC -05:00: 世界（复活节岛）时间</option>

  <option value="America/Bogota">UTC -05:00: 哥伦比亚时间</option>

  <option value="America/Havana">UTC -05:00: 古巴时间</option>

  <option value="America/Guayaquil">UTC -05:00: 厄瓜多尔（瓜亚基尔）时间</option>

  <option value="America/Port-au-Prince">UTC -05:00: 海地时间</option>

  <option value="America/Jamaica">UTC -05:00: 牙买加时间</option>

  <option value="America/Cayman">UTC -05:00: 开曼群岛时间</option>

  <option value="America/Cancun">UTC -05:00: 墨西哥（坎昆）时间</option>

  <option value="America/Panama">UTC -05:00: 巴拿马时间</option>

  <option value="America/Lima">UTC -05:00: 秘鲁时间</option>

  <option value="America/Grand_Turk">UTC -05:00: 特克斯和凯科斯群岛时间</option>

  <option value="America/New_York">UTC -05:00: 美国（纽约）时间</option>

  <option value="America/Detroit">UTC -05:00: 美国（底特律）时间</option>

  <option value="America/Kentucky/Louisville">UTC -05:00: 美国（路易斯维尔）时间</option>

  <option value="America/Kentucky/Monticello">UTC -05:00: 美国（肯塔基州蒙蒂塞洛）时间</option>

  <option value="America/Indiana/Indianapolis">UTC -05:00: 美国（印第安纳波利斯）时间</option>

  <option value="America/Indiana/Vincennes">UTC -05:00: 美国（印第安纳州温森斯）时间</option>

  <option value="America/Indiana/Winamac">UTC -05:00: 美国（印第安纳州威纳马克）时间</option>

  <option value="America/Indiana/Marengo">UTC -05:00: 美国（印第安纳州马伦戈）时间</option>

  <option value="America/Indiana/Petersburg">UTC -05:00: 美国（印第安纳州彼得斯堡）时间</option>

  <option value="America/Indiana/Vevay">UTC -05:00: 美国（印第安纳州维维市）时间</option>

  <option value="US/Eastern">UTC -05:00: 美国（纽约）时间</option>

  <option value="America/Antigua">UTC -04:00: 安提瓜和巴布达时间</option>

  <option value="America/Anguilla">UTC -04:00: 安圭拉时间</option>

  <option value="America/Aruba">UTC -04:00: 阿鲁巴时间</option>

  <option value="America/Barbados">UTC -04:00: 巴巴多斯时间</option>

  <option value="America/St_Barthelemy">UTC -04:00: 圣巴泰勒米时间</option>

  <option value="Atlantic/Bermuda">UTC -04:00: 百慕大时间</option>

  <option value="America/La_Paz">UTC -04:00: 玻利维亚时间</option>

  <option value="America/Kralendijk">UTC -04:00: 荷兰加勒比区时间</option>

  <option value="America/Campo_Grande">UTC -04:00: 巴西（大坎普）时间</option>

  <option value="America/Cuiaba">UTC -04:00: 巴西（库亚巴）时间</option>

  <option value="America/Porto_Velho">UTC -04:00: 巴西（波多韦柳）时间</option>

  <option value="America/Boa_Vista">UTC -04:00: 巴西（博阿维斯塔）时间</option>

  <option value="America/Manaus">UTC -04:00: 巴西（马瑙斯）时间</option>

  <option value="America/Halifax">UTC -04:00: 加拿大（哈利法克斯）时间</option>

  <option value="America/Glace_Bay">UTC -04:00: 加拿大（格莱斯贝）时间</option>

  <option value="America/Moncton">UTC -04:00: 加拿大（蒙克顿）时间</option>

  <option value="America/Goose_Bay">UTC -04:00: 加拿大（古斯湾）时间</option>

  <option value="America/Blanc-Sablon">UTC -04:00: 加拿大（布兰克萨布隆）时间</option>

  <option value="America/Curacao">UTC -04:00: 库拉索时间</option>

  <option value="America/Dominica">UTC -04:00: 多米尼克时间</option>

  <option value="America/Santo_Domingo">UTC -04:00: 多米尼加共和国时间</option>

  <option value="America/Grenada">UTC -04:00: 格林纳达时间</option>

  <option value="America/Thule">UTC -04:00: 格陵兰（图勒）时间</option>

  <option value="America/Guadeloupe">UTC -04:00: 瓜德罗普时间</option>

  <option value="America/Guyana">UTC -04:00: 圭亚那时间</option>

  <option value="America/St_Kitts">UTC -04:00: 圣基茨和尼维斯时间</option>

  <option value="America/St_Lucia">UTC -04:00: 圣卢西亚时间</option>

  <option value="America/Marigot">UTC -04:00: 法属圣马丁时间</option>

  <option value="America/Martinique">UTC -04:00: 马提尼克时间</option>

  <option value="America/Montserrat">UTC -04:00: 蒙特塞拉特时间</option>

  <option value="America/Puerto_Rico">UTC -04:00: 波多黎各时间</option>

  <option value="America/Lower_Princes">UTC -04:00: 荷属圣马丁时间</option>

  <option value="America/Port_of_Spain">UTC -04:00: 特立尼达和多巴哥时间</option>

  <option value="America/St_Vincent">UTC -04:00: 圣文森特和格林纳丁斯时间</option>

  <option value="America/Caracas">UTC -04:00: 委内瑞拉时间</option>

  <option value="America/Tortola">UTC -04:00: 英属维京群岛时间</option>

  <option value="America/St_Thomas">UTC -04:00: 美属维京群岛时间</option>

  <option value="America/St_Johns">UTC -03:30: 加拿大（圣约翰斯）时间</option>

  <option value="Antarctica/Palmer">UTC -03:00: 南极洲（帕默尔）时间</option>

  <option value="Antarctica/Rothera">UTC -03:00: 南极洲（罗瑟拉）时间</option>

  <option value="America/Argentina/Buenos_Aires">UTC -03:00: 阿根廷（布宜诺斯艾利斯）时间</option>

  <option value="America/Argentina/Cordoba">UTC -03:00: 阿根廷（科尔多瓦）时间</option>

  <option value="America/Argentina/Salta">UTC -03:00: 阿根廷（萨尔塔）时间</option>

  <option value="America/Argentina/Jujuy">UTC -03:00: 阿根廷（胡胡伊）时间</option>

  <option value="America/Argentina/Tucuman">UTC -03:00: 阿根廷（图库曼）时间</option>

  <option value="America/Argentina/Catamarca">UTC -03:00: 阿根廷（卡塔马卡）时间</option>

  <option value="America/Argentina/La_Rioja">UTC -03:00: 阿根廷（拉里奥哈）时间</option>

  <option value="America/Argentina/San_Juan">UTC -03:00: 阿根廷（圣胡安）时间</option>

  <option value="America/Argentina/Mendoza">UTC -03:00: 阿根廷（门多萨）时间</option>

  <option value="America/Argentina/San_Luis">UTC -03:00: 阿根廷（圣路易斯）时间</option>

  <option value="America/Argentina/Rio_Gallegos">UTC -03:00: 阿根廷（里奥加耶戈斯）时间</option>

  <option value="America/Argentina/Ushuaia">UTC -03:00: 阿根廷（乌斯怀亚）时间</option>

  <option value="America/Belem">UTC -03:00: 巴西（贝伦）时间</option>

  <option value="America/Fortaleza">UTC -03:00: 巴西（福塔雷萨）时间</option>

  <option value="America/Recife">UTC -03:00: 巴西（累西腓）时间</option>

  <option value="America/Araguaina">UTC -03:00: 巴西（阿拉瓜伊纳）时间</option>

  <option value="America/Maceio">UTC -03:00: 巴西（马塞约）时间</option>

  <option value="America/Bahia">UTC -03:00: 巴西（巴伊亚）时间</option>

  <option value="America/Sao_Paulo">UTC -03:00: 巴西（圣保罗）时间</option>

  <option value="America/Santarem">UTC -03:00: 巴西（圣塔伦）时间</option>

  <option value="America/Santiago">UTC -03:00: 智利时间</option>

  <option value="America/Punta_Arenas">UTC -03:00: 未知地区（Punta Arenas）时间</option>

  <option value="Atlantic/Stanley">UTC -03:00: 福克兰群岛时间</option>

  <option value="America/Cayenne">UTC -03:00: 法属圭亚那时间</option>

  <option value="America/Godthab">UTC -03:00: 格陵兰（戈特霍布）时间</option>

  <option value="America/Miquelon">UTC -03:00: 世界（密克隆）时间</option>

  <option value="America/Asuncion">UTC -03:00: 巴拉圭时间</option>

  <option value="America/Paramaribo">UTC -03:00: 苏里南时间</option>

  <option value="America/Montevideo">UTC -03:00: 乌拉圭时间</option>

  <option value="America/Noronha">UTC -02:00: 巴西（洛罗尼亚）时间</option>

  <option value="Atlantic/South_Georgia">UTC -02:00: 南乔治亚岛和南桑威齐群岛时间</option>

  <option value="Atlantic/Cape_Verde">UTC -01:00: 佛得角时间</option>

  <option value="America/Scoresbysund">UTC -01:00: 格陵兰（斯科列斯比桑德）时间</option>

  <option value="Atlantic/Azores">UTC -01:00: 葡萄牙（亚速尔群岛）时间</option>

  <option value="Antarctica/Troll">UTC +00:00: 世界（特罗尔）时间</option>

  <option value="Africa/Ouagadougou">UTC +00:00: 布基纳法索时间</option>

  <option value="Africa/Abidjan">UTC +00:00: 科特迪瓦时间</option>

  <option value="Atlantic/Canary">UTC +00:00: 西班牙（加那利）时间</option>

  <option value="Atlantic/Faroe">UTC +00:00: 法罗群岛时间</option>

  <option value="Europe/London">UTC +00:00: 英国时间</option>

  <option value="Europe/Guernsey">UTC +00:00: 根西岛时间</option>

  <option value="Africa/Accra">UTC +00:00: 加纳时间</option>

  <option value="America/Danmarkshavn">UTC +00:00: 格陵兰（丹马沙文）时间</option>

  <option value="Africa/Banjul">UTC +00:00: 冈比亚时间</option>

  <option value="Africa/Conakry">UTC +00:00: 几内亚时间</option>

  <option value="Africa/Bissau">UTC +00:00: 几内亚比绍时间</option>

  <option value="Europe/Dublin">UTC +00:00: 爱尔兰时间</option>

  <option value="Europe/Isle_of_Man">UTC +00:00: 曼岛时间</option>

  <option value="Atlantic/Reykjavik">UTC +00:00: 冰岛时间</option>

  <option value="Europe/Jersey">UTC +00:00: 泽西岛时间</option>

  <option value="Africa/Monrovia">UTC +00:00: 利比里亚时间</option>

  <option value="Africa/Bamako">UTC +00:00: 马里时间</option>

  <option value="Africa/Nouakchott">UTC +00:00: 毛里塔尼亚时间</option>

  <option value="Europe/Lisbon">UTC +00:00: 葡萄牙（里斯本）时间</option>

  <option value="Atlantic/Madeira">UTC +00:00: 葡萄牙（马德拉）时间</option>

  <option value="Atlantic/St_Helena">UTC +00:00: 圣赫勒拿时间</option>

  <option value="Africa/Freetown">UTC +00:00: 塞拉利昂时间</option>

  <option value="Africa/Dakar">UTC +00:00: 塞内加尔时间</option>

  <option value="Africa/Sao_Tome">UTC +00:00: 圣多美和普林西比时间</option>

  <option value="Africa/Lome">UTC +00:00: 多哥时间</option>

  <option value="GMT">GMT</option>

  <option value="UTC">UTC</option>

  <option value="Europe/Andorra">UTC +01:00: 安道尔时间</option>

  <option value="Europe/Tirane">UTC +01:00: 阿尔巴尼亚时间</option>

  <option value="Africa/Luanda">UTC +01:00: 安哥拉时间</option>

  <option value="Europe/Vienna">UTC +01:00: 奥地利时间</option>

  <option value="Europe/Sarajevo">UTC +01:00: 波斯尼亚和黑塞哥维那时间</option>

  <option value="Europe/Brussels">UTC +01:00: 比利时时间</option>

  <option value="Africa/Porto-Novo">UTC +01:00: 贝宁时间</option>

  <option value="Africa/Kinshasa">UTC +01:00: 刚果（金）（金沙萨）时间</option>

  <option value="Africa/Bangui">UTC +01:00: 中非共和国时间</option>

  <option value="Africa/Brazzaville">UTC +01:00: 刚果（布）时间</option>

  <option value="Europe/Zurich">UTC +01:00: 瑞士时间</option>

  <option value="Africa/Douala">UTC +01:00: 喀麦隆时间</option>

  <option value="Europe/Prague">UTC +01:00: 捷克共和国时间</option>

  <option value="Europe/Berlin">UTC +01:00: 德国（柏林）时间</option>

  <option value="Europe/Busingen">UTC +01:00: 德国（布辛根）时间</option>

  <option value="Europe/Copenhagen">UTC +01:00: 丹麦时间</option>

  <option value="Africa/Algiers">UTC +01:00: 阿尔及利亚时间</option>

  <option value="Africa/El_Aaiun">UTC +01:00: 西撒哈拉时间</option>

  <option value="Europe/Madrid">UTC +01:00: 西班牙（马德里）时间</option>

  <option value="Africa/Ceuta">UTC +01:00: 西班牙（休达）时间</option>

  <option value="Europe/Paris">UTC +01:00: 法国时间</option>

  <option value="Africa/Libreville">UTC +01:00: 加蓬时间</option>

  <option value="Europe/Gibraltar">UTC +01:00: 直布罗陀时间</option>

  <option value="Africa/Malabo">UTC +01:00: 赤道几内亚时间</option>

  <option value="Europe/Zagreb">UTC +01:00: 克罗地亚时间</option>

  <option value="Europe/Budapest">UTC +01:00: 匈牙利时间</option>

  <option value="Europe/Rome">UTC +01:00: 意大利时间</option>

  <option value="Europe/Vaduz">UTC +01:00: 列支敦士登时间</option>

  <option value="Europe/Luxembourg">UTC +01:00: 卢森堡时间</option>

  <option value="Africa/Casablanca">UTC +01:00: 摩洛哥时间</option>

  <option value="Europe/Monaco">UTC +01:00: 摩纳哥时间</option>

  <option value="Europe/Podgorica">UTC +01:00: 黑山时间</option>

  <option value="Europe/Skopje">UTC +01:00: 马其顿时间</option>

  <option value="Europe/Malta">UTC +01:00: 马耳他时间</option>

  <option value="Africa/Niamey">UTC +01:00: 尼日尔时间</option>

  <option value="Africa/Lagos">UTC +01:00: 尼日利亚时间</option>

  <option value="Europe/Amsterdam">UTC +01:00: 荷兰时间</option>

  <option value="Europe/Oslo">UTC +01:00: 挪威时间</option>

  <option value="Europe/Warsaw">UTC +01:00: 波兰时间</option>

  <option value="Europe/Belgrade">UTC +01:00: 塞尔维亚时间</option>

  <option value="Europe/Stockholm">UTC +01:00: 瑞典时间</option>

  <option value="Europe/Ljubljana">UTC +01:00: 斯洛文尼亚时间</option>

  <option value="Arctic/Longyearbyen">UTC +01:00: 斯瓦尔巴特和扬马延时间</option>

  <option value="Europe/Bratislava">UTC +01:00: 斯洛伐克时间</option>

  <option value="Europe/San_Marino">UTC +01:00: 圣马力诺时间</option>

  <option value="Africa/Ndjamena">UTC +01:00: 乍得时间</option>

  <option value="Africa/Tunis">UTC +01:00: 突尼斯时间</option>

  <option value="Europe/Vatican">UTC +01:00: 梵蒂冈时间</option>

  <option value="Europe/Mariehamn">UTC +02:00: 奥兰群岛时间</option>

  <option value="Europe/Sofia">UTC +02:00: 保加利亚时间</option>

  <option value="Africa/Bujumbura">UTC +02:00: 布隆迪时间</option>

  <option value="Africa/Gaborone">UTC +02:00: 博茨瓦纳时间</option>

  <option value="Africa/Lubumbashi">UTC +02:00: 刚果（金）（卢本巴希）时间</option>

  <option value="Asia/Nicosia">UTC +02:00: 塞浦路斯时间</option>

  <option value="Asia/Famagusta">UTC +02:00: 未知地区（Famagusta）时间</option>

  <option value="Europe/Tallinn">UTC +02:00: 爱沙尼亚时间</option>

  <option value="Africa/Cairo">UTC +02:00: 埃及时间</option>

  <option value="Europe/Helsinki">UTC +02:00: 芬兰时间</option>

  <option value="Europe/Athens">UTC +02:00: 希腊时间</option>

  <option value="Asia/Jerusalem">UTC +02:00: 以色列时间</option>

  <option value="Asia/Amman">UTC +02:00: 约旦时间</option>

  <option value="Asia/Beirut">UTC +02:00: 黎巴嫩时间</option>

  <option value="Africa/Maseru">UTC +02:00: 莱索托时间</option>

  <option value="Europe/Vilnius">UTC +02:00: 立陶宛时间</option>

  <option value="Europe/Riga">UTC +02:00: 拉脱维亚时间</option>

  <option value="Africa/Tripoli">UTC +02:00: 利比亚时间</option>

  <option value="Europe/Chisinau">UTC +02:00: 摩尔多瓦时间</option>

  <option value="Africa/Blantyre">UTC +02:00: 马拉维时间</option>

  <option value="Africa/Maputo">UTC +02:00: 莫桑比克时间</option>

  <option value="Africa/Windhoek">UTC +02:00: 纳米比亚时间</option>

  <option value="Asia/Gaza">UTC +02:00: 世界（加沙）时间</option>

  <option value="Asia/Hebron">UTC +02:00: 世界（希伯伦）时间</option>

  <option value="Europe/Bucharest">UTC +02:00: 罗马尼亚时间</option>

  <option value="Europe/Kaliningrad">UTC +02:00: 俄罗斯（加里宁格勒）时间</option>

  <option value="Africa/Kigali">UTC +02:00: 卢旺达时间</option>

  <option value="Africa/Khartoum">UTC +02:00: 苏丹时间</option>

  <option value="Asia/Damascus">UTC +02:00: 叙利亚时间</option>

  <option value="Africa/Mbabane">UTC +02:00: 斯威士兰时间</option>

  <option value="Europe/Kiev">UTC +02:00: 乌克兰（基辅）时间</option>

  <option value="Europe/Uzhgorod">UTC +02:00: 乌克兰（乌日哥罗德）时间</option>

  <option value="Europe/Zaporozhye">UTC +02:00: 乌克兰（扎波罗热）时间</option>

  <option value="Africa/Johannesburg">UTC +02:00: 南非时间</option>

  <option value="Africa/Lusaka">UTC +02:00: 赞比亚时间</option>

  <option value="Africa/Harare">UTC +02:00: 津巴布韦时间</option>

  <option value="Antarctica/Syowa">UTC +03:00: 南极洲（昭和）时间</option>

  <option value="Asia/Bahrain">UTC +03:00: 巴林时间</option>

  <option value="Europe/Minsk">UTC +03:00: 白俄罗斯时间</option>

  <option value="Africa/Djibouti">UTC +03:00: 吉布提时间</option>

  <option value="Africa/Asmara">UTC +03:00: 厄立特里亚时间</option>

  <option value="Africa/Addis_Ababa">UTC +03:00: 埃塞俄比亚时间</option>

  <option value="Asia/Baghdad">UTC +03:00: 伊拉克时间</option>

  <option value="Africa/Nairobi">UTC +03:00: 肯尼亚时间</option>

  <option value="Indian/Comoro">UTC +03:00: 科摩罗时间</option>

  <option value="Asia/Kuwait">UTC +03:00: 科威特时间</option>

  <option value="Indian/Antananarivo">UTC +03:00: 马达加斯加时间</option>

  <option value="Asia/Qatar">UTC +03:00: 卡塔尔时间</option>

  <option value="Europe/Moscow">UTC +03:00: 俄罗斯（莫斯科）时间</option>

  <option value="Europe/Simferopol">UTC +03:00: 俄罗斯（辛菲罗波尔）时间</option>

  <option value="Europe/Kirov">UTC +03:00: 未知地区（Kirov）时间</option>

  <option value="Asia/Riyadh">UTC +03:00: 沙特阿拉伯时间</option>

  <option value="Africa/Mogadishu">UTC +03:00: 索马里时间</option>

  <option value="Africa/Juba">UTC +03:00: 南苏丹时间</option>

  <option value="Europe/Istanbul">UTC +03:00: 土耳其时间</option>

  <option value="Africa/Dar_es_Salaam">UTC +03:00: 坦桑尼亚时间</option>

  <option value="Africa/Kampala">UTC +03:00: 乌干达时间</option>

  <option value="Asia/Aden">UTC +03:00: 也门时间</option>

  <option value="Indian/Mayotte">UTC +03:00: 马约特时间</option>

  <option value="Asia/Tehran">UTC +03:30: 伊朗时间</option>

  <option value="Asia/Dubai">UTC +04:00: 阿拉伯联合酋长国时间</option>

  <option value="Asia/Yerevan">UTC +04:00: 亚美尼亚时间</option>

  <option value="Asia/Baku">UTC +04:00: 阿塞拜疆时间</option>

  <option value="Asia/Tbilisi">UTC +04:00: 格鲁吉亚时间</option>

  <option value="Indian/Mauritius">UTC +04:00: 毛里求斯时间</option>

  <option value="Asia/Muscat">UTC +04:00: 阿曼时间</option>

  <option value="Indian/Reunion">UTC +04:00: 留尼汪时间</option>

  <option value="Europe/Astrakhan">UTC +04:00: 未知地区（Astrakhan）时间</option>

  <option value="Europe/Volgograd">UTC +04:00: 俄罗斯（伏尔加格勒）时间</option>

  <option value="Europe/Saratov">UTC +04:00: 未知地区（Saratov）时间</option>

  <option value="Europe/Ulyanovsk">UTC +04:00: 未知地区（Ulyanovsk）时间</option>

  <option value="Europe/Samara">UTC +04:00: 俄罗斯（萨马拉）时间</option>

  <option value="Indian/Mahe">UTC +04:00: 塞舌尔时间</option>

  <option value="Asia/Kabul">UTC +04:30: 阿富汗时间</option>

  <option value="Antarctica/Mawson">UTC +05:00: 南极洲（莫森）时间</option>

  <option value="Asia/Qyzylorda">UTC +05:00: 哈萨克斯坦（克孜洛尔达）时间</option>

  <option value="Asia/Aqtobe">UTC +05:00: 哈萨克斯坦（阿克托别）时间</option>

  <option value="Asia/Aqtau">UTC +05:00: 哈萨克斯坦（阿克套）时间</option>

  <option value="Asia/Atyrau">UTC +05:00: 未知地区（Atyrau）时间</option>

  <option value="Asia/Oral">UTC +05:00: 哈萨克斯坦（乌拉尔）时间</option>

  <option value="Indian/Maldives">UTC +05:00: 马尔代夫时间</option>

  <option value="Asia/Karachi">UTC +05:00: 巴基斯坦时间</option>

  <option value="Asia/Yekaterinburg">UTC +05:00: 俄罗斯（叶卡捷琳堡）时间</option>

  <option value="Indian/Kerguelen">UTC +05:00: 法属南部领地时间</option>

  <option value="Asia/Dushanbe">UTC +05:00: 塔吉克斯坦时间</option>

  <option value="Asia/Ashgabat">UTC +05:00: 土库曼斯坦时间</option>

  <option value="Asia/Samarkand">UTC +05:00: 乌兹别克斯坦（撒马尔罕）时间</option>

  <option value="Asia/Tashkent">UTC +05:00: 乌兹别克斯坦（塔什干）时间</option>

  <option value="Asia/Kolkata">UTC +05:30: 印度时间</option>

  <option value="Asia/Colombo">UTC +05:30: 斯里兰卡时间</option>

  <option value="Asia/Kathmandu">UTC +05:45: 尼泊尔时间</option>

  <option value="Antarctica/Vostok">UTC +06:00: 南极洲（沃斯托克）时间</option>

  <option value="Asia/Dhaka">UTC +06:00: 孟加拉国时间</option>

  <option value="Asia/Thimphu">UTC +06:00: 不丹时间</option>

  <option value="Asia/Urumqi">UTC +06:00: 中国（乌鲁木齐）时间</option>

  <option value="Indian/Chagos">UTC +06:00: 英属印度洋领地时间</option>

  <option value="Asia/Bishkek">UTC +06:00: 吉尔吉斯斯坦时间</option>

  <option value="Asia/Almaty">UTC +06:00: 哈萨克斯坦（阿拉木图）时间</option>

  <option value="Asia/Qostanay">UTC +06:00: 未知地区（Qostanay）时间</option>

  <option value="Asia/Omsk">UTC +06:00: 俄罗斯（鄂木斯克）时间</option>

  <option value="Indian/Cocos">UTC +06:30: 科科斯（基林）群岛时间</option>

  <option value="Asia/Yangon">UTC +06:30: 未知地区（Yangon）时间</option>

  <option value="Antarctica/Davis">UTC +07:00: 南极洲（戴维斯）时间</option>

  <option value="Indian/Christmas">UTC +07:00: 圣诞岛时间</option>

  <option value="Asia/Jakarta">UTC +07:00: 印度尼西亚（雅加达）时间</option>

  <option value="Asia/Pontianak">UTC +07:00: 印度尼西亚（坤甸）时间</option>

  <option value="Asia/Phnom_Penh">UTC +07:00: 柬埔寨时间</option>

  <option value="Asia/Vientiane">UTC +07:00: 老挝时间</option>

  <option value="Asia/Hovd">UTC +07:00: 蒙古（科布多）时间</option>

  <option value="Asia/Novosibirsk">UTC +07:00: 俄罗斯（诺沃西比尔斯克）时间</option>

  <option value="Asia/Barnaul">UTC +07:00: 未知地区（Barnaul）时间</option>

  <option value="Asia/Tomsk">UTC +07:00: 未知地区（Tomsk）时间</option>

  <option value="Asia/Novokuznetsk">UTC +07:00: 俄罗斯（新库兹涅茨克）时间</option>

  <option value="Asia/Krasnoyarsk">UTC +07:00: 俄罗斯（克拉斯诺亚尔斯克）时间</option>

  <option value="Asia/Bangkok">UTC +07:00: 泰国时间</option>

  <option value="Asia/Ho_Chi_Minh">UTC +07:00: 越南时间</option>

  <option value="Antarctica/Casey">UTC +08:00: 南极洲（卡塞）时间</option>

  <option value="Australia/Perth">UTC +08:00: 澳大利亚（珀斯）时间</option>

  <option value="Asia/Brunei">UTC +08:00: 文莱时间</option>

  <option value="Asia/Shanghai" selected="">UTC +08:00: 中国（上海）时间</option>

  <option value="Asia/Hong_Kong">UTC +08:00: 中国香港特别行政区时间</option>

  <option value="Asia/Makassar">UTC +08:00: 印度尼西亚（望加锡）时间</option>

  <option value="Asia/Ulaanbaatar">UTC +08:00: 蒙古（乌兰巴托）时间</option>

  <option value="Asia/Choibalsan">UTC +08:00: 蒙古（乔巴山）时间</option>

  <option value="Asia/Macau">UTC +08:00: 中国澳门特别行政区时间</option>

  <option value="Asia/Kuala_Lumpur">UTC +08:00: 马来西亚（吉隆坡）时间</option>

  <option value="Asia/Kuching">UTC +08:00: 马来西亚（古晋）时间</option>

  <option value="Asia/Manila">UTC +08:00: 菲律宾时间</option>

  <option value="Asia/Irkutsk">UTC +08:00: 俄罗斯（伊尔库茨克）时间</option>

  <option value="Asia/Singapore">UTC +08:00: 新加坡时间</option>

  <option value="Asia/Taipei">UTC +08:00: 台湾时间</option>

  <option value="Australia/Eucla">UTC +08:45: 世界（尤克拉）时间</option>

  <option value="Asia/Jayapura">UTC +09:00: 印度尼西亚（查亚普拉）时间</option>

  <option value="Asia/Tokyo">UTC +09:00: 日本时间</option>

  <option value="Asia/Pyongyang">UTC +09:00: 世界（平壤）时间</option>

  <option value="Asia/Seoul">UTC +09:00: 韩国时间</option>

  <option value="Pacific/Palau">UTC +09:00: 帕劳时间</option>

  <option value="Asia/Chita">UTC +09:00: 俄罗斯（赤塔）时间</option>

  <option value="Asia/Yakutsk">UTC +09:00: 俄罗斯（雅库茨克）时间</option>

  <option value="Asia/Khandyga">UTC +09:00: 俄罗斯（汉德加）时间</option>

  <option value="Asia/Dili">UTC +09:00: 东帝汶时间</option>

  <option value="Australia/Darwin">UTC +09:30: 澳大利亚（达尔文）时间</option>

  <option value="Antarctica/DumontDUrville">UTC +10:00: 南极洲（迪蒙迪尔维尔）时间</option>

  <option value="Australia/Brisbane">UTC +10:00: 澳大利亚（布里斯班）时间</option>

  <option value="Australia/Lindeman">UTC +10:00: 澳大利亚（林德曼）时间</option>

  <option value="Pacific/Chuuk">UTC +10:00: 密克罗尼西亚（特鲁克群岛）时间</option>

  <option value="Pacific/Guam">UTC +10:00: 关岛时间</option>

  <option value="Pacific/Saipan">UTC +10:00: 北马里亚纳群岛时间</option>

  <option value="Pacific/Port_Moresby">UTC +10:00: 巴布亚新几内亚（莫尔兹比港）时间</option>

  <option value="Asia/Vladivostok">UTC +10:00: 俄罗斯（符拉迪沃斯托克）时间</option>

  <option value="Asia/Ust-Nera">UTC +10:00: 俄罗斯（乌斯内拉）时间</option>

  <option value="Australia/Broken_Hill">UTC +10:30: 澳大利亚（布罗肯希尔）时间</option>

  <option value="Australia/Adelaide">UTC +10:30: 澳大利亚（阿德莱德）时间</option>

  <option value="Australia/Lord_Howe">UTC +11:00: 世界（豪勋爵）时间</option>

  <option value="Antarctica/Macquarie">UTC +11:00: 澳大利亚（麦格理）时间</option>

  <option value="Australia/Hobart">UTC +11:00: 澳大利亚（霍巴特）时间</option>

  <option value="Australia/Currie">UTC +11:00: 澳大利亚（库利）时间</option>

  <option value="Australia/Melbourne">UTC +11:00: 澳大利亚（墨尔本）时间</option>

  <option value="Australia/Sydney">UTC +11:00: 澳大利亚（悉尼）时间</option>

  <option value="Pacific/Pohnpei">UTC +11:00: 密克罗尼西亚（波纳佩岛）时间</option>

  <option value="Pacific/Kosrae">UTC +11:00: 密克罗尼西亚（库赛埃）时间</option>

  <option value="Pacific/Noumea">UTC +11:00: 新喀里多尼亚时间</option>

  <option value="Pacific/Bougainville">UTC +11:00: 巴布亚新几内亚（布干维尔）时间</option>

  <option value="Asia/Magadan">UTC +11:00: 俄罗斯（马加丹）时间</option>

  <option value="Asia/Sakhalin">UTC +11:00: 俄罗斯（萨哈林）时间</option>

  <option value="Asia/Srednekolymsk">UTC +11:00: 俄罗斯（中科雷姆斯克）时间</option>

  <option value="Pacific/Guadalcanal">UTC +11:00: 所罗门群岛时间</option>

  <option value="Pacific/Efate">UTC +11:00: 瓦努阿图时间</option>

  <option value="Pacific/Tarawa">UTC +12:00: 基里巴斯（塔拉瓦）时间</option>

  <option value="Pacific/Majuro">UTC +12:00: 马绍尔群岛（马朱罗）时间</option>

  <option value="Pacific/Kwajalein">UTC +12:00: 马绍尔群岛（夸贾林）时间</option>

  <option value="Pacific/Norfolk">UTC +12:00: 世界（诺福克）时间</option>

  <option value="Pacific/Nauru">UTC +12:00: 瑙鲁时间</option>

  <option value="Asia/Kamchatka">UTC +12:00: 俄罗斯（堪察加）时间</option>

  <option value="Asia/Anadyr">UTC +12:00: 俄罗斯（阿纳德尔）时间</option>

  <option value="Pacific/Funafuti">UTC +12:00: 图瓦卢时间</option>

  <option value="Pacific/Wake">UTC +12:00: 美国本土外小岛屿（威克）时间</option>

  <option value="Pacific/Wallis">UTC +12:00: 瓦利斯和富图纳时间</option>

  <option value="Antarctica/McMurdo">UTC +13:00: 南极洲（麦克默多）时间</option>

  <option value="Pacific/Fiji">UTC +13:00: 斐济时间</option>

  <option value="Pacific/Enderbury">UTC +13:00: 基里巴斯（恩德伯里）时间</option>

  <option value="Pacific/Auckland">UTC +13:00: 新西兰时间</option>

  <option value="Pacific/Fakaofo">UTC +13:00: 托克劳时间</option>

  <option value="Pacific/Tongatapu">UTC +13:00: 汤加时间</option>

  <option value="Pacific/Chatham">UTC +13:45: 世界（查塔姆）时间</option>

  <option value="Pacific/Kiritimati">UTC +14:00: 基里巴斯（基里地马地岛）时间</option>

  <option value="Pacific/Apia">UTC +14:00: 萨摩亚时间</option>

</select>
                       </Field>

                        <Field name="aboutme" cols="5">
                            <textarea></textarea>
                        </Field>
                    </Panel>
                    <footer>
                        <input type="submit" />&nbsp;
                        <Link to="/ViewProfile/Profile"><Text name="View" /></Link>
                    </footer>
                </form>
            </View>
        );
    }

    render() {
        return  <Panel layout="border" left={this.getPhotoRender()} center={this.getProfileRender()} />;
    }
}

Profile.contextType = EnvContext;

Profile.propTypes = {
    user : PropTypes.object.isRequired
}

class Account extends React.Component {

    constructor(props) {
        super(props);
        this.user = props.user;
        this.password = React.createRef();
        this.password2 = React.createRef();
        this.errorDlg = React.createRef();
    }

    SaveProfilePassword(e) {
        e.preventDefault();

        let password = this.password.current.value;
        let password2 = this.password2.current.value;
        if (password != password2) {
            this.errorDlg.current.show();
            return false;
        }

        ajax.post("SaveProfilePassword.do", { password: password} )
        .then((response)=>{
            if (response.data.error == false) {
                this.props.history.push("/ViewProfile/Account");
            }
        })
        .catch(ex=>{
            console.error(ex);
        });

    }

    render() {
        return (
            <React.Fragment>
                <ErrorDialog ref={this.errorDlg}><Text name="tip.PasswordNotMatch" /></ErrorDialog>
                <View view="Login" title="Account">
                    <form autoComplete="off" onSubmit={(e)=>this.SaveProfilePassword(e)}>
                        <Panel layout="grid" cols="1">
                            <Field name="loginname">********</Field>
                            <Field name="password">
                                <input type="password" name="password" defaultValue="******" required={true}  ref={this.password}/>
                            </Field>
                            <Field name="password">
                                <input type="password" defaultValue="******" required={true}  ref={this.password2}/>
                            </Field>
                        </Panel>
                        <footer>
                            <input type="submit" />&nbsp;
                            <Link to="/ViewProfile/Account"><Text name="View" /></Link>
                        </footer>			
                     </form>
                </View>
            </React.Fragment>
        );
    }
}

Account.propTypes = {
    user : PropTypes.object.isRequired
}


class Customize extends React.Component {
    constructor(props) {
        super(props);
        this.user = props.user;

        this.state = {
            dateformat : this.user.customize.dateformat,
            timeformat : this.user.customize.timeformat,
            datetimeformat: this.user.customize.datetimeformat,
            pagesize : this.user.customize.pagesize,
            style : this.user.customize.style
        }
    }

    SaveProfileCustomize(e) {
        e.preventDefault();
        let params = {
            dateformat : this.state.dateformat,
            timeformat : this.state.timeformat,
            datetimeformat : this.state.datetimeformat,
            pagesize : this.state.pagesize,
            style : this.state.style
        };

        ajax.post("SaveProfileCustomize.do", params)
        .then((response)=>{
            if (response.data.error == false) {
                ((u)=>this.context.setUser(u))(response.data.object);
                this.props.history.push("/ViewProfile/Customize");
            }
        })
        .catch(ex=>{
            console.error(ex);
        });
    }

    toggleStyle(e) {
        this.context.getThemeService.applyTheme(e.target.value);
        this.setState({style: e.target.value});
    }

    render() {
        return (
            <View view="Customize" >
            <form autoComplete="off" onSubmit={(e)=>this.SaveProfileCustomize(e)}>
                <Panel layout="grid" cols="3">
                    <Field name="dataformat">
                        <select value={this.state.dateformat} onChange={(e)=>this.setState({dateformat: e.target.value})}>
                            <option value="yyyy-MM-dd">yyyy-MM-dd</option>
                            <option value="MM/dd/yyyy">MM/dd/yyyy</option>
                        </select>
                    </Field>

                    <Field name="timeformat">
                        <select value={this.state.timeformat} onChange={(e)=>this.setState({timeformat: e.target.value})}>
                            <option value="HH:mm">HH:mm</option>
                            <option value="HH:mm:ss">HH:mm:ss</option>
                        </select>
                    </Field>
                    <Field name="datatimeformat">
                        <select value={this.state.datetimeformat} onChange={(e)=>this.setState({datatimeformat: e.target.value})}>
                            <option value="yyyy-MM-dd HH:mm">yyyy-MM-dd HH:mm</option>
                            <option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option>
                            <option value="MM/dd/yyyy HH:mm">MM/dd/yyyy HH:mm</option>
                        </select>
                    </Field>
                    <Field name="pagesize">
                        <input type="range" min="5" max="50" step="5" defaultValue={this.state.pagesize} />                    
                    </Field>
                    <Field name="style">
                        <select value={this.state.style}  onChange={(e)=>this.toggleStyle(e)}>
                            <option value="orange" >{getText("orange")}</option>
                            <option value="dark" >{getText("dark")}</option>
                        </select>
                    </Field>
                </Panel>
                <footer>
                    <input type="submit" />&nbsp;
                    <Link to="/ViewProfile/Customize"><Text name="View" /></Link>
                </footer>	
            </form>
            </View>
        );
    }
}

Customize.contextType = EnvContext;

Customize.propTypes = {
    user : PropTypes.object.isRequired
}


export default class ModifyProfile extends React.Component {

    constructor(props) {
        super(props);
        this.active = this.props.match && this.props.match.params.active ? this.props.match.params.active : "Profile";
    }

    render() {
        return (
            <Tabs active={this.active}>
                <Tab key="Profile" title="Profile"><Profile  {...this.props}/></Tab>
                <Tab key="Account" title="Account"><Account  {...this.props}/></Tab>
                <Tab key="Customize" title="Customize"><Customize  {...this.props}/></Tab>
            </Tabs>
        );
    }
}
