<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import { Poules } from "@/stores/user/poule"

  import BannerVue from "../../components/historique/banner.vue";
  import PouleListVue from '../../components/PouleList.vue';

  import { useRoute } from "vue-router";
  import { ref, computed} from 'vue'

  const tournoiStore = Tournois();
  const pouleStore = Poules();
  const route = useRoute();

  const actif = "Poules";
  const can_edit = false;
  const show_cards = false;
  const show_poules_menu =true;

  const year = computed(()=>{
    return route.params.year
  } )
  tournoiStore.getTournoiInfo()

  pouleStore.getAllByYear(year.value)
  const poules = computed(()=>pouleStore.getPoules );

</script>
<template>
  <div class="flex flex-col">
    <BannerVue :year="year" base_link="/historiques/" :actif="actif" :show_poules_menu="show_poules_menu"/>
    <PouleListVue :poules="poules" :can_edit="can_edit" :show_cards="show_cards"/>
  </div>  
</template>

