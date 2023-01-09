<script setup>
import { ref, defineProps, computed } from "vue";
import { Poules } from "@/stores/user/poule"


const pouleStore = Poules();
const show = ref(false);
const props = defineProps({match:Object});
const id = props.match.id;
const score_equipe_1 = ref(props.match.score1);
const score_equipe_2 = ref(props.match.score2);

const errors = computed(() => { 
  return pouleStore.errors; 
  }) 



function validateFields() {
  if(score_equipe_1.value  == null || score_equipe_2.value == null){
    return false;
  }
}


async function store() {
    pouleStore.errors =""

    if(validateFields() === false){
      pouleStore.errors = "Tous les champs sont requis !";
    }
    
    else{

      await pouleStore.renseigner_score(id, {
        "scoreA":score_equipe_1.value,
        "scoreB":score_equipe_2.value,
        "id":id
      })
      if(errors == ""){
          toggleModal();
      }
        
    }
    
}

function toggleModal() {
  show.value = !show.value;
}




</script>

<template>

    <i class="fa-thin fa-pen text-dark-brown cursor-pointer font-bold" @click="toggleModal" title="Renseigner Score"></i>

  <div
    v-if="show"
    id="popup"
    class="z-50 fixed w-full flex justify-center inset-0 bg-transparent"
  >
    <div
      @click="toggleModal()"
      class="w-full h-full bg-black bg-opacity-40 absolute inset-0"
    ></div>
    <div class="flex items-center justify-center h-full w-full ">
      <div
        class=" bg-white text-white rounded-lg shadow fixed h-auto w-auto px-4 md:px-20 md:py-10"
      >
      <i class="fa-regular fa-xmark fixed absolute right-5 top-5 text-black text-xl font-bold cursor-pointer" @click="toggleModal()"></i>
        
        <div class="">
          
            <form @submit.prevent="store" class="flex flex-col items-center justify-center">

                <div class="flex flex-col text-red-300 text-center tracking-wider">
                    <p 
                        class="text-sm md:text-md font-normal py-2"
                        v-if="errors"
                    >
                        {{ errors }}
                    </p>
                </div>
            

                <div class="mt-6 w-full text-dark-brown flex flex-col">
                    <label class="text-md font-normal leading-none "> {{ match.equipe1 }}</label>
                    <input
                        v-model="score_equipe_1"
                        aria-label="Entrer Score"
                        role="input"
                        type="number"
                        min="0"
                        placeholder="Entrer Score"
                        class="tracking-wider poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>

                <div class="mt-6 w-full text-dark-brown flex flex-col">
                    <label class="text-md font-normal leading-none "> {{ match.equipe2 }} </label>
                    <input
                        v-model="score_equipe_2"
                        aria-label="Entrer Score"
                        role="input"
                        type="number"
                        min="0"
                        placeholder="Entrer Score"
                        class="tracking-wider poppins bg-white border border-dark-brown rounded-lg focus:outline-none text-sm font-medium leading-none py-3 w-full pl-3 mt-2"
                    />
                </div>


            
                <div class="flex items-center justify-center w-full mt-7 mb-2">
                    <button
                        type="submit"
                        aria-label="add user"
                        role="button"
                        class="focus:ring-2 focus:ring-offset-2 focus:ring-clear-brown focus:outline-none px-6 py-3 bg-clear-brown hover:bg-opacity-80 shadow rounded-xl md:text-md text-sm text-white w-full font-bold"
                    >
                        Enregistrer
                    </button>
                </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</template>