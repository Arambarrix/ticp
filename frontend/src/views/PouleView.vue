<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import PouleListVue from '../components/PouleList.vue';
  import { computed } from "vue";
  import { Tournois } from "@/stores/user/tournoi"
  import { Poules } from "@/stores/user/poule"

  const tournoiStore = Tournois();
  const pouleStore = Poules();

  const actif = "Poules";
  const can_edit = true;
  const show_cards = true;

  tournoiStore.getTournoiInfo()

  pouleStore.getAllByYear(new Date().getFullYear())
  const poules = computed(()=>pouleStore.getPoules );
</script>

<template>
  <main>
    <BannerVue :actif="actif"/>
    <PouleListVue :poules="poules" :can_edit="can_edit" :show_cards="show_cards"/>

  </main>
  
</template>