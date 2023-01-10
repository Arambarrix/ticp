<script setup> 
    import { ref, computed} from 'vue'
    import HistoriqueYearsVue from "./HistoriqueYears.vue";
    import BannerButtonListVue from '../banner/ButtonList.vue'; 

    const props = defineProps({'year':String, 'base_link':String});
    var actif = computed(() =>  props.actif);

    var historique_links= computed(() => {
    
        return {
            'tournois':[{'id':'2023'},{'id':'2022'},{'id':'2021'},{'id':'2020'},{'id':'2019'},{'id':'2018'},
            {'id':'2023'},{'id':'2022'},{'id':'2021'},{'id':'2020'},{'id':'2019'},{'id':'2018'},
            {'id':'2023'},{'id':'2022'},{'id':'2021'},{'id':'2020'},{'id':'2019'},{'id':'2018'},
        ], 
            'actif':actif.value, 
            'base_link':props.base_link, 
        };

    });


    var banner_data= computed(() => {
        return {
            'list_text_links':[{'text':'Résumé','link':"/poules" }, {'text':'Poules','link':"/poules" },{'text':'Tableaux','link':"/tableaux/1" } ,{'text':'Equipes','link':"/equipes" } ], 
            'actif':actif.value, 
            'is_fully_rounded':true
        };
      
    });

</script>

<template>

    <div class="flex  flex-col">
        <HistoriqueYearsVue  v-bind="historique_links"  class="w-full px-6 md:px-8"/>
        <div class="relative divbody rounded-b-lg">
            <img src="../../assets/img/image2.jpg" class="w-full h-auto opacity-75" alt="banner image" />
            <div class="bottom-1/3 left-1/2 md:left-1/4 -translate-x-1/2 translate-y-2/3 absolute md:w-2/5">
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