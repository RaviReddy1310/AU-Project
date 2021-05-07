import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FileService } from '../file.service';
import { LoginService } from '../login.service';
import { TrainingMaterials } from '../training-materials';
import { User } from '../user';

@Component({
  selector: 'app-all-versions',
  templateUrl: './all-versions.component.html',
  styleUrls: ['./all-versions.component.css']
})
export class AllVersionsComponent implements OnInit {

  user : User;
  materials ?: TrainingMaterials[];
  cid:number;

  constructor(private fileService:FileService,
              private loginService:LoginService,
              private router:Router,
              private activatedroute:ActivatedRoute,
              private location:Location) {
                this.user = this.loginService.getWithExpiry('user');
                if(!this.user) {
                  this.router.navigate([`/login`]);
                }

                this.cid = Number(this.activatedroute.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    this.fileService.allVersions(this.cid).subscribe((response:any) => {
      this.materials = response;
      if(this.materials === null) {
        alert("No versions available. First you need to add files");
        this.router.navigate([`trainer-courses`]);
      }
    })
  }

  downloadfile(data:any, type:string) {
    this.fileService.downloadMaterial(data,type);
  }

  deleteVersion(id:number) {
    this.fileService.deleteVersion(id).subscribe((response:any) => {
      alert(response);
      window.location.reload();
    })
  }

  goBack() {
    this.location.back();
  }

}
