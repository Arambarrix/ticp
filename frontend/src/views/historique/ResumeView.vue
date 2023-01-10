<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import { Teams } from "@/stores/user/team"

  import BannerVue from "../../components/historique/banner.vue";
  import TeamListVue from '../../components/TeamList.vue';
  import InfoCardVue from "../../components/InfoCard.vue";

  import { useRoute } from "vue-router";
  import { onMounted, computed} from 'vue'

  const tournoiStore = Tournois();
  const teamStore = Teams();
  const route = useRoute();

  const actif = "Résumé";
  const show_poules_menu =true;

  const year = computed(()=>{
    return route.params.year
  } )

  tournoiStore.getTournoiInfo(year.value)
  
  const tournoi_tableaux_length = computed(()=>tournoiStore.getTableauxLength);
  const tournoi_equipes_length = computed(()=>tournoiStore.getEquipesLength);
  const tournoi_poules_length = computed(()=>tournoiStore.getPoulesLength);

  var infoCardDatas= computed(()=>{
    return {
        "equipe":{'image':'user.png', 'number': tournoi_equipes_length.value, 'text':"Equipes Inscrites", 'color':"#1B2A5A"},
        "poule":{'image':'group.png', 'number':tournoi_poules_length.value, 'text':"Poules Générées", 'color':"#195937"},
        "tableau":{'image':'network.png', 'number':tournoi_tableaux_length.value, 'text':"Tableaux crées", 'color':"#00253A"}
    } 
  })  

  console.log(infoCardDatas.value)


  teamStore.getAllByYear(new Date().getFullYear())
  const teams = computed(()=>teamStore.getTeams );

</script>
<template>
  <div class="flex flex-col space-y-10">
    <BannerVue :year="year" base_link="/historiques/" :actif="actif" :show_poules_menu="show_poules_menu"/>
    <p class="font-bold text-clear-brown text-center text-md md:text-xl pb-3">Résumé de la compétition {{year}}</p>

    <div class="grid grid-cols-2 md:grid-cols-3 gap-y-8 gap-x-16 justify-items-stretch mb-16">
        <InfoCardVue v-bind="infoCardDatas.equipe"/>
        <InfoCardVue v-bind="infoCardDatas.poule"/>
        <InfoCardVue v-bind="infoCardDatas.tableau"/>
    </div>
    
    <div>
      <p class="font-bold text-clear-brown text-center text-md md:text-xl">Vainqueurs</p>
      <TeamListVue :teams="teams" show_header="false"/>

    </div>

  </div>  
</template>

