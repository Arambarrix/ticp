<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import TeamListVue from '../components/TeamList.vue';
  import { computed } from "vue";

  import { Tournois } from "@/stores/user/tournoi"
  import { Teams } from "@/stores/user/team"

  const teamStore = Teams();
  const tournoiStore = Tournois();

  tournoiStore.getActualTournoiInfo()


  var actif = "Equipes";
   
  teamStore.getAllByYear(new Date().getFullYear())
  const teams = computed(()=>teamStore.getTeams );
   
</script>

<template>
  <main>
    <BannerVue :actif="actif"/>
    <TeamListVue :teams="teams" />
  </main>
  
</template>