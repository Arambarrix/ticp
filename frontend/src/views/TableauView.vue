<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import TableauListVue from '../components/TableauList.vue';
  import InfoCardVue from '../components/InfoCard.vue';
  import { useRoute,useRouter, RouterLink } from "vue-router";
  import { ref, computed} from 'vue'
  import { Tournois } from "@/stores/user/tournoi"

  const route = useRoute();
  const router = useRouter()
  const tournoiStore = Tournois();
  tournoiStore.getActualTournoiInfo()

  var is_poules_created = true;
  var actif = "Tableaux";
  var tableau_colors=["#FBBF24", "#9CA3AF", "#cd7f32"]
  var tableaux = [{"nom":"Or", "rang":1}, {"nom":"argent", "rang":2}, {"nom":"Bronze", "rang":3}, {"nom":"Autre", "rang":4}]
  var infoCardDatas={
        "equipe":{'image':'user.png', 'number':"89", 'text':"Equipes Inscrites", 'color':"#1B2A5A"},
        "match":{'image':'flag.png', 'number':"15", 'text':"Matchs Joués", 'color':"#3A0000"},
        "poule":{'image':'group.png', 'number':"12", 'text':"Poules Générées", 'color':"#195937"},
        "tableau":{'image':'network.png', 'number':"3", 'text':"Tableaux crées", 'color':"#00253A"}
  }

  function previous(){
    if(rang.value != 1){
      router.push({
                  name: 'tableaux',
                  params: { rang: parseInt(rang.value)-1 }
              })
    } 
  }

  function next(){
    if(rang.value != tableaux.length){
      router.push({
                  name: 'tableaux',
                  params: { rang: parseInt(rang.value)+1 }
              })
    } 
  }
  
  const rang = computed(()=>{
    return route.params.rang
  } )

  var cssVars = computed(() => {
    if (rang.value <= 3){
      return {
        '--bg-color': tableau_colors[parseInt(rang.value)-1],
      }
    }
    else{
      const randomColor = Math.floor(Math.random()*16777215).toString(16)
      return {
        '--bg-color': '#'+randomColor,
      }
    }
      
  });
</script>

<template>
  <main>
    <BannerVue :actif="actif"/>
    <div class="py-10 ">

      <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
          <InfoCardVue v-bind="infoCardDatas.equipe"/>
          <InfoCardVue v-bind="infoCardDatas.match"/>
          <InfoCardVue v-bind="infoCardDatas.poule"/>
          <InfoCardVue v-bind="infoCardDatas.tableau"/>
      </div>

      <div class="tableauColor flex flex-row justify-center space-x-10 text-white items-center font-bold py-2  rounded-lg mb-12" :style="cssVars">
        <i class="fa-regular fa-angle-left cursor-pointer"  @click="previous()"></i>
        <span class="text-lg sm:text-xl md:text-3xl">Tableau <span class="capitalize">{{tableaux[rang-1].nom}}</span></span>
        <i class="fa-regular fa-angle-right cursor-pointer" @click="next()"></i>

      </div>


      <TableauListVue v-if="is_poules_created"/>
    </div>
  </main>
  
</template>

<style scoped>
    .tableauColor {
        background-color: var(--bg-color);
    }
</style>