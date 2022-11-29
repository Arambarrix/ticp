<script setup>
  import BannerButtonListVue from './ButtonList.vue';
  import BaseVue from './Base.vue';

  import { ref, computed } from 'vue'

  const props = defineProps({'is_begin':Boolean});

  var is_begin = computed(() => {
        return props.is_begin
    });

  var banner_data= computed(() => {
    if(!is_begin.value){
      return {'list_text_links':[{'text':'Poules','link':"/poules" },{'text':'Tableaux','link':"/tableaux" } ,{'text':'Matchs','link':"/tableaux" } ,{'text':'Equipes','link':"/tableaux" } ], 'actif':"Tableaux", 'is_fully_rounded':true};
    }
    else{
      return {'list_text_links':[{'text':'Participer','link':"/inscrire_equipe" }], 'actif':"Participer", 'is_fully_rounded':false};
    }
  });

  var banner_header= computed(() => {
    if(!is_begin.value){
      return "Le Tournoi 2022 est lancé";
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
