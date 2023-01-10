<script setup> 
    import HistoriqueYearsVue from "./HistoriqueYears.vue";
    import BannerButtonListVue from '../banner/ButtonList.vue'; 

    import { Tournois } from "@/stores/user/tournoi"
    import { ref, computed} from 'vue'

    const props = defineProps({'year':String, 'base_link':String, 'actif':String, 'show_poules_menu':Boolean});
    
    const actif = computed(() =>  props.actif);
    const tournoiStore = Tournois();

    tournoiStore.getAll()
    const tournois = computed(()=>tournoiStore.getTournois );

    var historique_links= computed(() => {
    
        return {
            'tournois':tournois.value,
            'year_actif':props.year, 
            'base_link':props.base_link, 
        };

    });


    var banner_data= computed(() => {
        return {
            'list_text_links':[{'text':'Résumé','link':"/historiques/"+props.year }, {'text':'Poules','link':"/historiques/"+props.year+"/poules" },{'text':'Tableaux','link':"/historiques/"+props.year+"/tableaux/0" } ,{'text':'Equipes','link':"/historiques/"+props.year+"/equipes" } ], 
            'actif':actif.value, 
            'is_fully_rounded':true
        };
      
    });
</script>

<template>

    <div class="flex  flex-col">
        <HistoriqueYearsVue  v-bind="historique_links" class="w-full px-6 md:px-8"/>
        <div class="relative divbody rounded-b-lg">
            <img src="../../assets/img/image2.jpg" class="w-full h-auto opacity-75" alt="banner image" />
            <div v-if="show_poules_menu" class="bottom-1/3 left-1/2 md:left-1/4 -translate-x-1/2 translate-y-2/3 absolute md:w-2/5">
                <BannerButtonListVue v-bind="banner_data"/>
            </div>
        </div>  
    </div>
      
</template>

<style scoped>
    .divbody {
        background: linear-gradient(180.37deg, rgba(0, 0, 0, 0) 30.89%, #000000 99.68%);
    }
</style>