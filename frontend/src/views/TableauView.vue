<script setup>
  import BannerVue from '../components/banner/Banner.vue';
  import TableauListVue from '../components/TableauList.vue';
  import InfoCardVue from '../components/InfoCard.vue';
  import { useRoute,useRouter, RouterLink } from "vue-router";
  import { ref, computed} from 'vue'
  import { Tournois } from "@/stores/user/tournoi"
import { Tableaux } from '../stores/user/tableau';

  const route = useRoute();
  const router = useRouter()
  const tournoiStore = Tournois();
  const tableaustore= Tableaux();

  tournoiStore.getTournoiInfo()

  const rang = computed(()=>{
    return route.params.rang
  } )
  tableaustore.getTab(parseInt(rang.value))
  
  const tableau = computed(()=>tableaustore.getTableau)

  var actif = "Tableaux";
  var can_edit = true;

  var tableau_colors=["#FBBF24", "#9CA3AF", "#cd7f32"]
  var tableaux = [{"nom":"Or", "rang":1}, {"nom":"argent", "rang":2}, {"nom":"Bronze", "rang":3}, {"nom":"Autre", "rang":4}]

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
  

  var cssVars = computed(() => {
    if (rang.value <= 3){
      return {
        '--bg-color': tableau_colors[parseInt(rang.value)-1],
      }
    }
    else{
      const randomColor = generateDarkColorHex()
      return {
        '--bg-color': randomColor,
      }
    }
      
  });

  function generateDarkColorHex() {
    let color = "#";
    for (let i = 0; i < 3; i++)
      color += ("0" + Math.floor(Math.random() * Math.pow(16, 2) / 2).toString(16)).slice(-2);
    return color;
  }
</script>

<template>
  <main>
    <BannerVue :actif="actif"/>
    <div class="py-10 ">

      <div class="grid grid-cols-2 md:grid-cols-3 gap-y-8 gap-x-16 justify-items-stretch mb-16">
          <InfoCardVue v-bind="infoCardDatas.equipe"/>
          <InfoCardVue v-bind="infoCardDatas.poule"/>
          <InfoCardVue v-bind="infoCardDatas.tableau"/>
      </div>

      <div class="tableauColor flex flex-row justify-center space-x-10 text-white items-center font-bold py-2  rounded-lg mb-12" :style="cssVars">
        <i class="fa-regular fa-angle-left cursor-pointer"  @click="previous()"></i>
        <span class="text-lg sm:text-xl md:text-3xl">Tableau <span class="capitalize">{{tableaux[rang-1].nom}}</span></span>
        <i class="fa-regular fa-angle-right cursor-pointer" @click="next()"></i>

      </div>


      <TableauListVue  :tours="tableau.tours" :can_edit="can_edit"/>
    </div>
  </main>
  
</template>

<style scoped>
    .tableauColor {
        background-color: var(--bg-color);
    }
</style>