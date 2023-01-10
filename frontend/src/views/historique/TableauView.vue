<script setup>
  import BannerVue from "../../components/historique/banner.vue";
  import TableauListVue from "../../components/TableauList.vue";
  import { Tournois } from "@/stores/user/tournoi"
  import { Teams } from "@/stores/user/team"
  import { useRoute,useRouter } from "vue-router";
  import { ref, computed} from 'vue'

  const tournoiStore = Tournois();
  const teamStore = Teams();
  const route = useRoute();
  const router = useRouter()

  const actif = "Tableaux";
  const show_poules_menu =true;
  const can_edit = false;

  const year = computed(()=>{
    return route.params.year
  } )
  
  tournoiStore.getTournoiInfo()


  var tableau_colors=["#FBBF24", "#9CA3AF", "#cd7f32"]
  var tableaux = [{"nom":"Or", "rang":1}, {"nom":"argent", "rang":2}, {"nom":"Bronze", "rang":3}, {"nom":"Autre", "rang":4}]
  var infoCardDatas={
        "equipe":{'image':'user.png', 'number':"89", 'text':"Equipes Inscrites", 'color':"#1B2A5A"},
        "poule":{'image':'group.png', 'number':"12", 'text':"Poules Générées", 'color':"#195937"},
        "tableau":{'image':'network.png', 'number':"3", 'text':"Tableaux crées", 'color':"#00253A"}
  }

  function previous(){
    if(rang.value != 1){
      router.push({
                  name: 'historique_tableaux',
                  params: { year: year.value, rang: parseInt(rang.value)-1 }
              })
    } 
  }

  function next(){
    if(rang.value != tableaux.length){
      router.push({
                  name: 'historique_tableaux',
                  params: {year: year.value,  rang: parseInt(rang.value)+1 }
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
  <div class="flex flex-col space-y-10">
    <BannerVue :year="year" base_link="/historiques/" :actif="actif" :show_poules_menu="show_poules_menu"/>

    <div class="py-10 ">

      <div class="tableauColor flex flex-row justify-center space-x-10 text-white items-center font-bold py-2  rounded-lg mb-12" :style="cssVars">
        <i class="fa-regular fa-angle-left cursor-pointer"  @click="previous()"></i>
        <span class="text-lg sm:text-xl md:text-3xl">Tableau <span class="capitalize">{{tableaux[rang-1].nom}}</span></span>
        <i class="fa-regular fa-angle-right cursor-pointer" @click="next()"></i>

      </div>

      <TableauListVue  :can_edit="can_edit"/>
    </div>
    

  </div>  
</template>

<style scoped>
    .tableauColor {
        background-color: var(--bg-color);
    }
</style>
