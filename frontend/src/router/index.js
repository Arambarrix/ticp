import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/historiques/:year',
      name: 'historique',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/ResumeView.vue')
    },
    {
      path: '/historiques/:year/poules',
      name: 'historique_poules',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/PouleView.vue')
    },

    {
      path: '/historiques/:year/tableaux/:rang',
      name: 'historique_tableaux',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/TableauView.vue')
    },

    {
      path: '/historiques/:year/equipes',
      name: 'historique_equipes',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/TeamView.vue')
    },

    {
      path: '/galerie/:year',
      name: 'galerie',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/GalerieView.vue')
    },

    {
      path: '/admin/:year',
      name: 'admin',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/admin/HomeView.vue')
    },

    {
      path: '/connexion',
      name: 'connexion',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ConnexionView.vue')
    },

    {
      path: '/poules',
      name: 'poules',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/PouleView.vue')
      },


    {
      path: '/tableaux/:rang',
        name: 'tableaux',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/TableauView.vue'),
      },

  
      
    {
      path: '/equipes',
      name: 'equipes',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/TeamView.vue')
    },

    {
      path: '/inscrire_equipe',
      name: 'inscrire_equipe',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/CreateTeamView.vue')
    },

    {
        path: '/admin/accueil/:year',
        name: 'accueil',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/HomeView.vue')
      },

      {
        path: '/admin/creer_tournoi',
        name: 'creer_tournoi',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/CreateTournoiView.vue')
      },


      {
          path: '/admin/information_utile',
          name: 'admin_information_utile',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/admin/InformationUtile.vue')
      },

      {
          path: '/admin/photo/:year',
          name: 'photo_admin',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/admin/Photo.vue')
      },

  ]
})

export default router
