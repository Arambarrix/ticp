<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import BannerVue from "../components/historique/banner.vue";
  import { useRoute } from "vue-router";
  import { ref, computed} from 'vue'

  const tournoiStore = Tournois();
  const route = useRoute();

  const year = computed(()=>{
    return route.params.year
  } )
  tournoiStore.getActualTournoiInfo()
  var actif = "Poules";

 


</script>
<template>
  <BannerVue :year="year" base_link="/galerie" :actif="actif"/>
</template>

