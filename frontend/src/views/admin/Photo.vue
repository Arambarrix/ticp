<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
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

    tournoiStore.getTournoiInfo()

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
            'https://cdn.pixabay.com/photo/2019/07/21/22/32/bule-4353765_960_720.jpg',
        },
        {
            id: 2,
            url:
            'https://cdn.pixabay.com/photo/2015/09/21/15/45/balls-950050_960_720.jpg',
        },
        {
            id: 3,
            url:
            'https://cdn.pixabay.com/photo/2017/08/25/16/59/bowling-2680738_960_720.jpg',
        },
        {
            id: 1,
            url:
                'https://img.freepik.com/photos-premium/boules-petanque-dans-boite-herbe_524824-1528.jpg?w=740',
        },
        {
            id: 2,
            url:
                'https://img.freepik.com/photos-gratuite/amis-plein-coup-jouant-petanque-ensemble_23-2149530380.jpg?w=740&t=st=1673362515~exp=1673363115~hmac=137e71e1ac4111ddd9f84414d7700c6f25465a7a4f7d56f2fa661790de4dfc9b',
        },
        {
            id: 3,
            url:
                'https://img.freepik.com/photos-gratuite/hommes-faible-angle-jouant-plage_23-2149530353.jpg?w=740&t=st=1673362489~exp=1673363089~hmac=af2534f52df8c6fd2968253421d4c8ef1dbccb9f8f1ccc7a2f887e72ff5e66cb',
        },
        {
            id: 1,
            url:
                'https://img.freepik.com/photos-gratuite/vue-laterale-homme-lancant-balle_23-2149530335.jpg?t=st=1673362489~exp=1673363089~hmac=699581c2eb7c5363c74575849dfe26f1d62137dda61cefaae97fd874de085bfe',
        },
        {
            id: 2,
            url:
                'https://img.freepik.com/photos-gratuite/vue-laterale-amis-heureux-jouant-au-jeu-exterieur_23-2149530351.jpg?w=740&t=st=1673362838~exp=1673363438~hmac=cad899b090ae15c60fd9b4ef969f4f1707203557176134e0a8a7733a0f4435de',
        },
        {
            id: 3,
            url:
                'https://img.freepik.com/photos-gratuite/amis-ages-jouant-petanque_23-2149443926.jpg?w=740&t=st=1673362846~exp=1673363446~hmac=af4ff7e78db322d34b1abeb875394497c8d197c7f6f5915e8fd9daece0ca291a',
        },
        {
            id: 1,
            url:
                'https://img.freepik.com/photos-gratuite/amis-ages-jouant-petanque_23-2149443890.jpg?t=st=1673362846~exp=1673363446~hmac=81ed85eed8723954705726653e232af04db20fb377c57dce3cc95726c81b8710',
        },
        {
            id: 2,
            url:
                'https://img.freepik.com/photos-gratuite/vue-laterale-mains-tenant-boules-rouges-plage_23-2149530360.jpg?w=740&t=st=1673362808~exp=1673363408~hmac=048e1d8a7683da4a26c1bf4f204934ac3020bc5a33752157a0ade22870592446',
        },
        {
            id: 3,
            url:
                'https://img.freepik.com/photos-gratuite/amis-ages-jouant-petanque_23-2149443921.jpg?w=740&t=st=1673362835~exp=1673363435~hmac=0dd3037cf2e6d1a81d7a250e147b0bfaf8bcdec0d170c1980a6555f7da970310',
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