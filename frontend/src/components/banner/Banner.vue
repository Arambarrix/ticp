<script setup>
  import BannerButtonListVue from './ButtonList.vue';
  import BaseVue from './Base.vue';
  import { Tournois } from "@/stores/user/tournoi"
  import { computed } from 'vue'

  const props = defineProps({'actif':String});
  const tournoiStore = Tournois();

  const is_tournoi_launched = computed(()=>tournoiStore.isTournoiLaunched );
  const is_registration_ended = computed(()=>tournoiStore.isRegistrationEnded );
  var actif = computed(() =>  props.actif);

  console.log(is_tournoi_launched.value, is_registration_ended.value)

  var banner_data= computed(() => {

    if(is_tournoi_launched.value && !is_registration_ended.value){
      console.log("participer", is_tournoi_launched.value && !is_registration_ended.value)
      return {'list_text_links':[{'text':'Participer','link':"/inscrire_equipe" }], 'actif':"Participer", 'is_fully_rounded':false};
    }

    if(is_tournoi_launched.value && is_registration_ended.value){
      console.log("poulessss", is_tournoi_launched.value && is_registration_ended.value)
      return {'list_text_links':[{'text':'Poules','link':"/poules" },{'text':'Tableaux','link':"/tableaux/1" } ,{'text':'Equipes','link':"/equipes" } ], 'actif':actif.value, 'is_fully_rounded':true};
    }
    
    return {'list_text_links':[], 'actif':"", 'is_fully_rounded':false};
  });


  var banner_header= computed(() => {
    if(is_tournoi_launched.value){
      return "Le Tournoi "+new Date().getFullYear()+" est lancé";
    }
    else{
      return "Bienvenue au Tournoi Annuel de Pétanque";
    }
  });

</script>

<template>
    <BaseVue>
      <template #header>
        <h1>{{banner_header}}</h1>
      </template>

      <template #buttons>
        <BannerButtonListVue v-bind="banner_data"/>
      </template>

    </BaseVue>
</template>
