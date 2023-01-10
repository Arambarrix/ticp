import { defineStore } from "pinia";
import { Constants } from "@/stores/constants";
import router from "@/router";
import Cookies from "universal-cookie";
const cookies = new Cookies();

export const useUploadStore = defineStore({
  id: "uploads",
  state: () => ({
    access: cookies.get("access") || "",
    medias: [],
    cloudName:"murie",
    CLOUDINARY_UPLOAD_PRESET: "preset",
    CLOUDINARY_URL: "https://api.cloudinary.com/v1_1/murie/upload'",
    isError: false,
    errorText: null
  }),
  getters: {
    all: (state) => state.sites,
  },
  actions: {
    
    async onAddFiles(files) {
      if (files.length > 0) {
        files.forEach((file) => {
          console.log(file);
          this.uploadFileToCloudinary(file).then((fileResponse) => {
            this.medias.push(fileResponse);
          });
          console.log(this.medias)
        });
      }
    },
    reset(){
      this.medias=[];
      this.isError = false;
      this.errorText = null;
    },

    uploadFileToCloudinary(file) {
      return new Promise(function (resolve, reject) {
        const CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/murie/upload';
        const CLOUDINARY_UPLOAD_PRESET = 'preset';

        let formData = new FormData();
        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET);
        formData.append('folder', 'cloudinary-demo');
        formData.append('file', file);

        let request = new XMLHttpRequest();
        request.open('POST', CLOUDINARY_URL, true);
        request.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

        request.onreadystatechange = () => {
          // File uploaded successfully
          if (request.readyState === 4 && request.status === 200) {
            let response = JSON.parse(request.responseText);
            resolve(response);
          }

          // Not succesfull, let find our what happened
          if (request.status !== 200) {
            let response = JSON.parse(request.responseText);
            let error = response.error.message;
            alert('error, status code not 200 ' + error);
            reject(error);
          }

        };

        request.onerror = (err) => {
          alert('error: ' + err);
          reject(err);
        };

        request.send(formData);
      });
    },
  },
});
