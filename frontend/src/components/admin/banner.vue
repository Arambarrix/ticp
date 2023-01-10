<script setup> 
    import { ref, computed} from 'vue'
    import HistoriqueYearsVue from "./HistoriqueYears.vue";

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

</script>

<template>

    <div class="flex  flex-col">
        <HistoriqueYearsVue  v-bind="historique_links"  class="w-full p-4 mb-6"/>  
    </div>
      
</template>

<style scoped>
    .divbody {
        background: linear-gradient(180.37deg, rgba(0, 0, 0, 0) 30.89%, #000000 99.68%);
    }
</style>