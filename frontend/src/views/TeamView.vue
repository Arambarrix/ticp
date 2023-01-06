<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import TeamListVue from '../components/TeamList.vue';
  import { computed, onMounted } from "vue";
  import { Teams } from "@/stores/user/team"

  const teamStore = Teams();

  var is_poules_created = true;
  var actif = "Equipes";
/*
  const teams = computed(()=>{
        return [{'name':"BABA", 'nb_joueur':5}, {'name':"Nom Equipe", 'nb_joueur':5}, {'name':"BABA", 'nb_joueur':5},
        {'name':"Nom Equipe long", 'nb_joueur':5},{'name':"Nom Equipe très long", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5}, {'name':"BABA", 'nb_joueur':5}, {'name':"Nom Equipe", 'nb_joueur':5}, {'name':"BABA", 'nb_joueur':5},
        {'name':"Nom Equipe long", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5},{'name':"BABA", 'nb_joueur':5}]
    });
    */
   
    teamStore.getAllByYear(new Date().getFullYear())
    const teams = computed(()=>teamStore.getTeams );
   
</script>

<template>
  <main>
    <BannerVue :actif="actif"/>
    <TeamListVue v-if="is_poules_created" :teams="teams" :total="0"/>
  </main>
  
</template>