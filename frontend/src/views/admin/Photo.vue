<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import BoutonGenerate from '../../components/admin/ButtonGenerate.vue';
    import GaleryView from '../../components/admin/Galery.vue';
    import BannerVue from "../../components/admin/banner.vue";
    import { Tournois } from "@/stores/user/tournoi"
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { ref, computed } from 'vue'
    const tournoiStore = Tournois();
    const route = useRoute();
    const router = useRouter()
    const year = computed(() => {
        return route.params.rang
    })

    tournoiStore.getActualTournoiInfo()

    var infoCardDatas = {
        "photos": { 'image': 'galerie.png', 'number': "310", 'text': "Photos", 'color': "#195937" }
    };



    var poule_button = computed(() => {
        return {
            'button_info': { 'link': "/admin/poules/", 'text': "Poules" }, 'actif': "Poules", 'is_fully_rounded': true
        }
    });

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
    <main>

        <div class="py-5 snap-x">

            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
                <InfoCardVue v-bind="infoCardDatas.photos" />
            </div>
            <div class="gap-y-32">
                <BannerVue :year="year" base_link="/admin/photo/"/>
                <p></p>
            </div>
            <div class="w-48 justify-items-stretch">
                <div class="justify-between text-white bg-green-700 text-xl p-2 rounded-xl gap-4">
                    <RouterLink to="/" class="relative flex flex-row items-center text-white-600 pr-6">
                        <i class="fa-solid fa-plus justify-center items-center ml-4"></i>
                        <span class="ml-2 text-sm tracking-wide truncate">Nouvelle Photo</span>
                    </RouterLink>
                </div>
                <p class="my-5 text-dark-brown text-xl md:text-2xl font-bold">Tournois</p>
            </div>
            <div class="max-w-5xl justify-items-stretch">
                <GaleryView :images="images"/>
            </div>
        </div>
    </main>
</template>