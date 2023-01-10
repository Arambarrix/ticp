<script setup>
  import BannerVue from "../../components/historique/banner.vue";
  import TableauListVue from "../../components/TableauList.vue";
  import { Tournois } from "@/stores/user/tournoi"
  import { Tableaux } from "@/stores/user/tableau"
  import { useRoute,useRouter } from "vue-router";
  import { watch, computed} from 'vue'

  const tournoiStore = Tournois();
  const tableaustore= Tableaux();
  const route = useRoute();
  const router = useRouter()

  const actif = "Tableaux";
  const show_poules_menu =true;
  const can_edit = false;

  const rang = computed(()=>{
    return route.params.rang
  } )

  const year = computed(()=>{
    return route.params.year
  } )

  tournoiStore.getTournoiInfo()
  tableaustore.getTab(parseInt(rang.value), year.value)
  tableaustore.getAll(year.value)
  const tableaux = computed(()=>tableaustore.getTableaux)
  const tableau = computed(()=>tableaustore.getTableau)
  watch(rang, rangChanged)
  watch(year, yearChanged)


  var tableau_colors=["#FBBF24", "#9CA3AF", "#cd7f32"]
  var trio = [{"nom":"Or", "rang":1}, {"nom":"argent", "rang":2}, {"nom":"Bronze", "rang":3}, {"nom":"Autre", "rang":4}]
  

  function previous(){
    if(rang.value != 0){
      router.push({
                  name: 'historique_tableaux',
                  params: {year:year.value, rang: parseInt(rang.value)-1 }
              })
    } 
  }

  function next(){
    if(rang.value != tableaux.value.length-1 && tableaux.value.length){
      router.push({
                  name: 'historique_tableaux',
                  params: { year:year.value, rang: parseInt(rang.value)+1 }
              })
    } 
  }
  

  var cssVars = computed(() => {
    if (rang.value < 3){
      return {
        '--bg-color': tableau_colors[parseInt(rang.value)],
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

  function rangChanged(rang){
    tableaustore.getTab(rang, year.value)
  }

  function yearChanged(year){
    tableaustore.getTab(rang.value, year)
  }

</script>
<template>
  <div class="flex flex-col space-y-10">
    <BannerVue :year="year" base_link="/historiques/" :actif="actif" :show_poules_menu="show_poules_menu"/>

    <div class="py-10 ">

      <div class="tableauColor flex flex-row justify-center space-x-10 text-white items-center font-bold py-2  rounded-lg mb-12" :style="cssVars">
        <i class="fa-regular fa-angle-left cursor-pointer"  @click="previous()"></i>
        <span class="text-lg sm:text-xl md:text-3xl">Tableau 
            <span v-if="rang < 3" class="capitalize">{{trio[rang].nom}}</span>

            <span  v-else class="capitalize">{{tableaux[rang].rang + 1 }}</span>

       </span>
        <i class="fa-regular fa-angle-right cursor-pointer" @click="next()"></i>

      </div>

      <TableauListVue  :tours="tableau.tours" :rang="rang" :can_edit="can_edit"/>
    </div>
    

  </div>  
</template>

<style scoped>
    .tableauColor {
        background-color: var(--bg-color);
    }
</style>
