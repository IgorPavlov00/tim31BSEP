export class Crt {
  id:number;
  commonName: string;
  organizationUnit: string;
  organizationName: string;
  localityName: string;
  stateName: string;
  countryName: string;
  filepath:string;
  valid:boolean


  constructor() {
    this.id=null;
    this.commonName = '';
    this.organizationUnit = '';
    this.organizationName = '';
    this.localityName = '';
    this.stateName = '';
    this.countryName = '';
    this.filepath='';
    this.valid=true;
  }
}
