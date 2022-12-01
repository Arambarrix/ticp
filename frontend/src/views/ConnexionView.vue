<template>
     <section>
      <div class="container">
          <form v-on:submit.prevent="submit">
              <div class="form-group">
                  <label for="identifiant">Identifiant:</label>
                  <input type="text" id="identifiant" v-model.trim="$v.identifiant.$model">
              </div>
              
              <div class="form-group">
                  <label for="motDePasse">Mot de passe:</label>
                  <input type="password" id="motdepasse" v-model.trim="$v.password.$model">
              </div>
              <button class="bg-[#3A1900]" type="submit">Se connecter</button>
          </form>
      </div>
  </section>
</template>

<script>
    import { required } from '@vuelidate/lib/validators'
    export default{
        name:'connexionView',
        data: function () {
            return{
                identifiant:'',
                motdepasse:''
            }
            
        },
        validations:{
            identifiant:{required},
            motdepasse:{required}
        },

        methods:{
            submit:function(e){

                this.$v.touch();
                if(this.$v.$spendding || this.$v.$error) return;
                alert('Data Submit');
            }

            
        }
    }
</script>

<style>
* {
  box-sizing: border-box;
  font-family: "Montserrat";
}
section {
  height: 90vh;
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.container {
  width: 90%;
  max-width: 550px;
  margin: 0 auto;
  padding: 20px;
  box-shadow: 0px 0px 20px #00000033;
  border-radius: 8px;
}
.form-group {
  margin-top: 20px;
  font-size: 20px;
  color: #9e9e9e;
}
.form-group input,
.form-group textarea {
  width: 100%;
  padding: 5px;
  font-size: 18px;
  border: 1px solid rgba(128, 128, 128, 0.199);
  margin-top: 5px;
}
textarea {
  resize: vertical;
}

button {
    margin-left: 27%;
    margin-top: 10%;
  height: 45px;
  width: 200px;
  align-items: center;
  color: white;
  font-size: 24px;
  border-radius: 20px;
  cursor: pointer;
  margin-top: 10px;
}
</style>