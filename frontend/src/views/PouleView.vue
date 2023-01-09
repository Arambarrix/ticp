<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import PouleListVue from '../components/PouleList.vue';
  import { computed } from "vue";
  import { Tournois } from "@/stores/user/tournoi"
  import { Poules } from "@/stores/user/poule"

  const tournoiStore = Tournois();
  const pouleStore = Poules();

  tournoiStore.getActualTournoiInfo()

  var actif = "Poules";

  pouleStore.getAllByYear(new Date().getFullYear())
  const poules = computed(()=>pouleStore.getPoules );
</script>

<template>
  <main>
    <BannerVue :actif="actif"/>
    <PouleListVue :poules="poules"/>

  </main>
  
</template>