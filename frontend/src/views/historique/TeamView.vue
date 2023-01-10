<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import { Teams } from "@/stores/user/team"

  import BannerVue from "../../components/historique/banner.vue";
  import TeamListVue from '../../components/TeamList.vue';

  import { useRoute } from "vue-router";
  import { computed} from 'vue'

  const tournoiStore = Tournois();
  const teamStore = Teams();
  const route = useRoute();

  const actif = "Equipes";
  const show_poules_menu =true;

  const year = computed(()=>{
    return route.params.year
  } )

  tournoiStore.getTournoiInfo()
 
  teamStore.getAllByYear(year.value)
  const teams = computed(()=>teamStore.getTeams );

</script>
<template>
  <div class="flex flex-col space-y-10">
    <BannerVue :year="year" base_link="/historiques/" :actif="actif" :show_poules_menu="show_poules_menu"/>
    <TeamListVue :teams="teams" show_header="true"/>
  </div>  
</template>

