<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import BoutonGenerate from '../../components/admin/ButtonGenerate.vue';
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { ref, computed } from 'vue'
    const route = useRoute();
    const router = useRouter()
    const year = computed(() => {
        return route.params.rang
    })

    var infoCardDatas = {
        "photos": { 'image': 'galerie.png', 'number': "310", 'text': "Photos", 'color': "#195937" }
    };



    var poule_button = computed(() => {
        return {
            'button_info': { 'link': "/admin/poules/", 'text': "Poules" }, 'actif': "Poules", 'is_fully_rounded': true
        }
    });

</script>

<template>
    <main>

        <div class="py-10 snap-x">

            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
                <InfoCardVue v-bind="infoCardDatas.photos" />
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

        </div>
    </main>
</template>