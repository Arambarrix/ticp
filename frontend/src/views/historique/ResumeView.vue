<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import { Teams } from "@/stores/user/team"

  import BannerVue from "../../components/historique/banner.vue";
  import TeamListVue from '../../components/TeamList.vue';
  import InfoCardVue from "../../components/InfoCard.vue";

  import { useRoute } from "vue-router";
  import { watch, computed} from 'vue'

  const tournoiStore = Tournois();
  const teamStore = Teams();
  const route = useRoute();

  const actif = "Résumé";
  const show_poules_menu =true;

  const year = computed(()=>{
    return route.params.year
  } )

  watch(year, yearChanged)


  tournoiStore.getTournoiInfo(year.value)
  tournoiStore.getVainqueursByYear(year.value)

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

  const vainqueurs = computed(()=>tournoiStore.getVainqueurs );

  function yearChanged(year){
    tournoiStore.getTournoiInfo(year)
    tournoiStore.getVainqueursByYear(year)
  }
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
      <TeamListVue  v-if="vainqueurs.length" :teams="vainqueurs" show_header="false" />
      <p v-else class="text-center my-5 text-red-500"> Pas de vainqueurs pour le moment</p>
    </div>

  </div>  
</template>

