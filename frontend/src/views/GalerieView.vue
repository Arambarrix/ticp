<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import BannerVue from "../components/historique/banner.vue";
  import { useRoute } from "vue-router";
  import { ref, computed} from 'vue'

  const tournoiStore = Tournois();
  const route = useRoute();

  const year = computed(()=>{
    return route.params.year
  } )
  
  tournoiStore.getTournoiInfo()

  const show_poules_menu = false;

 var medias = [{"resource_type":"image", "url":""}, {"resource_type":"image", "url":""}, {"resource_type":"image", "url":""}]
</script>
<template>
  <div class="flex flex-col space-y-10">
    <BannerVue :year="year" base_link="/galerie/" :actif="actif" :show_poules_menu="show_poules_menu"/>
    <p class="font-bold text-clear-brown text-center text-md md:text-xl pb-3">Tournoi {{year}}</p>

    <div class="flex w-[96%] space-x-5 overflow-x-auto">
      <div v-for="media in medias">
          <img
          v-if="media.resource_type =='image'"
          :src="media.url"
          alt="Photo de publication"
          class="w-64 rounded-xl h-48 object-cover object-center"
        />
          <video 
          v-if="media.resource_type =='video'"
          class="w-64 rounded-xl h-48 object-cover object-center" controls>
            <source :src="media.url" :type="'video/'+media.format">
            Your browser does not support the video display.
        </video> 
      </div>
    </div>
  </div>
</template>

