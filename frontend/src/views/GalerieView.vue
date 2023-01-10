<script setup>
  import { Tournois } from "@/stores/user/tournoi"
  import BannerVue from "../components/historique/banner.vue";
  import GaleryVue from "../components/admin/Galery.vue";

  import { useRoute } from "vue-router";
  import { ref, computed} from 'vue'

  const tournoiStore = Tournois();
  const route = useRoute();

  const year = computed(()=>{
    return route.params.year
  } )
  
  tournoiStore.getTournoiInfo()

  const show_poules_menu = false;

  var images = [
        {
            id: 1,
            url:
            'https://cdn.pixabay.com/photo/2021/11/11/13/08/leopard-6786267__340.jpg',
        },
        {
            id: 2,
            url:
            'https://cdn.pixabay.com/photo/2015/09/21/15/45/balls-950050_960_720.jpg',
        },
        {
            id: 3,
            url:
            'https://cdn.pixabay.com/photo/2020/09/23/17/12/flowers-5596564__340.png',
        },
        {
            id: 1,
            url:
                'https://cdn.pixabay.com/photo/2021/11/11/13/08/leopard-6786267__340.jpg',
        },
        {
            id: 2,
            url:
                'https://cdn.pixabay.com/photo/2020/07/01/17/34/wolf-5360340__340.jpg',
        },
        {
            id: 3,
            url:
                'https://cdn.pixabay.com/photo/2020/09/23/17/12/flowers-5596564__340.png',
        },
        {
            id: 1,
            url:
                'https://cdn.pixabay.com/photo/2021/11/11/13/08/leopard-6786267__340.jpg',
        },
        {
            id: 2,
            url:
                'https://cdn.pixabay.com/photo/2020/07/01/17/34/wolf-5360340__340.jpg',
        },
        {
            id: 3,
            url:
                'https://cdn.pixabay.com/photo/2020/09/23/17/12/flowers-5596564__340.png',
        },
        {
            id: 1,
            url:
                'https://cdn.pixabay.com/photo/2021/11/11/13/08/leopard-6786267__340.jpg',
        },
        {
            id: 2,
            url:
                'https://cdn.pixabay.com/photo/2020/07/01/17/34/wolf-5360340__340.jpg',
        },
        {
            id: 3,
            url:
                'https://cdn.pixabay.com/photo/2020/09/23/17/12/flowers-5596564__340.png',
        },
        {
            id: 1,
            url:
                'https://cdn.pixabay.com/photo/2021/11/11/13/08/leopard-6786267__340.jpg',
        },
        {
            id: 2,
            url:
                'https://cdn.pixabay.com/photo/2020/07/01/17/34/wolf-5360340__340.jpg',
        },
        {
            id: 3,
            url:
                'https://cdn.pixabay.com/photo/2020/09/23/17/12/flowers-5596564__340.png',
        }
    ]

</script>
<template>
  <div class="flex flex-col space-y-10">
    <BannerVue :year="year" base_link="/galerie/" :actif="actif" :show_poules_menu="show_poules_menu"/>
    <p class="font-bold text-clear-brown text-center text-md md:text-xl pb-3">Tournoi {{year}}</p>

    <RouterLink to="/" class="relative flex flex-row items-center text-white-600 pr-6">
        <i class="fa-solid fa-plus justify-center items-center ml-4"></i>
        <span class="ml-2 text-sm tracking-wide truncate">Nouvelle Photo</span>
    </RouterLink>
    <GaleryVue :images="images"/>
  </div>
</template>

