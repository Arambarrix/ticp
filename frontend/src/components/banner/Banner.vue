<script setup>
  import BannerButtonListVue from './ButtonList.vue';
  import BaseVue from './Base.vue';

  import { computed } from 'vue'

  const props = defineProps({'is_poules_created':Boolean, 'actif':String});

  var is_poules_created = computed(() => {
        return props.is_poules_created
    });
  var actif = computed(() => {
      return props.actif
  });

  var banner_data= computed(() => {
    if(is_poules_created.value){
      return {'list_text_links':[{'text':'Poules','link':"/poules" },{'text':'Tableaux','link':"/tableaux" } ,{'text':'Matchs','link':"/tableaux" } ,{'text':'Equipes','link':"/equipes" } ], 'actif':actif.value, 'is_fully_rounded':true};
    }
    else{
      return {'list_text_links':[{'text':'Participer','link':"/inscrire_equipe" }], 'actif':"Participer", 'is_fully_rounded':false};
    }
  });

  var banner_header= computed(() => {
    if(is_poules_created.value){
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
